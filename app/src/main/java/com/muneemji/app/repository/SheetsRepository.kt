package com.muneemji.app.repository

import android.content.Context
import com.muneemji.app.db.TransactionDao
import com.muneemji.app.db.TransactionEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

data class SheetsConnection(
    val spreadsheetId: String,
    val lastSyncedAt: Long
)

@Singleton
class SheetsRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val transactionDao: TransactionDao
) {
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getConnection(): SheetsConnection? {
        val spreadsheetId = preferences.getString(KEY_SPREADSHEET_ID, null)?.takeIf { it.isNotBlank() }
            ?: return null
        return SheetsConnection(
            spreadsheetId = spreadsheetId,
            lastSyncedAt = preferences.getLong(KEY_LAST_SYNCED_AT, 0L)
        )
    }

    suspend fun connect(accessToken: String): SheetsConnection = withContext(Dispatchers.IO) {
        getConnection() ?: createSpreadsheet(accessToken).also { connection ->
            saveConnection(connection.spreadsheetId)
        }
    }

    suspend fun exportTransactions(accessToken: String): Int = withContext(Dispatchers.IO) {
        val connection = connect(accessToken)
        val transactions = transactionDao.getAllTransactionsOnce()

        replaceValues(
            accessToken = accessToken,
            spreadsheetId = connection.spreadsheetId,
            range = "$EXPENSES_SHEET!A1:H",
            values = transactions.toExpenseRows()
        )
        replaceValues(
            accessToken = accessToken,
            spreadsheetId = connection.spreadsheetId,
            range = "$CATEGORIES_SHEET!A1:B",
            values = transactions.toCategoryRows()
        )
        replaceValues(
            accessToken = accessToken,
            spreadsheetId = connection.spreadsheetId,
            range = "$MONTHLY_SUMMARY_SHEET!A1:C",
            values = transactions.toMonthlySummaryRows()
        )

        saveLastSyncedAt()
        transactions.size
    }

    suspend fun importTransactions(accessToken: String): Int = withContext(Dispatchers.IO) {
        val connection = connect(accessToken)
        val response = getJson(
            accessToken = accessToken,
            endpointUrl = "$SHEETS_API_BASE/${connection.spreadsheetId}/values/${"$EXPENSES_SHEET!A2:H".urlEncode()}"
        )
        val rows = response.optJSONArray("values") ?: JSONArray()
        val transactions = mutableListOf<TransactionEntity>()

        for (index in 0 until rows.length()) {
            val row = rows.optJSONArray(index) ?: continue
            transactions.add(row.toTransactionEntity())
        }

        if (transactions.isNotEmpty()) {
            transactionDao.clearAll()
            transactionDao.insertAll(transactions)
        }

        saveLastSyncedAt()
        transactions.size
    }

    private fun createSpreadsheet(accessToken: String): SheetsConnection {
        val payload = JSONObject()
            .put("properties", JSONObject().put("title", SPREADSHEET_TITLE))
            .put(
                "sheets",
                JSONArray()
                    .put(sheetPayload(EXPENSES_SHEET))
                    .put(sheetPayload(CATEGORIES_SHEET))
                    .put(sheetPayload(MONTHLY_SUMMARY_SHEET))
            )

        val response = postJson(
            accessToken = accessToken,
            endpointUrl = SHEETS_API_BASE,
            payload = payload
        )
        val spreadsheetId = response.optString("spreadsheetId")
        require(spreadsheetId.isNotBlank()) { "Google Sheets did not return a spreadsheet ID." }

        replaceValues(
            accessToken = accessToken,
            spreadsheetId = spreadsheetId,
            range = "$EXPENSES_SHEET!A1:H",
            values = listOf(EXPENSE_HEADERS)
        )
        replaceValues(
            accessToken = accessToken,
            spreadsheetId = spreadsheetId,
            range = "$CATEGORIES_SHEET!A1:B",
            values = listOf(listOf("Category", "Total"))
        )
        replaceValues(
            accessToken = accessToken,
            spreadsheetId = spreadsheetId,
            range = "$MONTHLY_SUMMARY_SHEET!A1:C",
            values = listOf(listOf("Month", "Total", "Transactions"))
        )

        return SheetsConnection(spreadsheetId = spreadsheetId, lastSyncedAt = 0L)
    }

    private fun replaceValues(
        accessToken: String,
        spreadsheetId: String,
        range: String,
        values: List<List<Any?>>
    ) {
        clearValues(accessToken, spreadsheetId, range)
        appendValues(accessToken, spreadsheetId, range, values)
    }

    private fun appendValues(
        accessToken: String,
        spreadsheetId: String,
        range: String,
        values: List<List<Any?>>
    ) {
        val payload = JSONObject()
            .put("majorDimension", "ROWS")
            .put("values", values.toJsonArray())

        postJson(
            accessToken = accessToken,
            endpointUrl = "$SHEETS_API_BASE/$spreadsheetId/values/${range.urlEncode()}:append?valueInputOption=RAW&insertDataOption=INSERT_ROWS",
            payload = payload
        )
    }

    private fun clearValues(accessToken: String, spreadsheetId: String, range: String) {
        postJson(
            accessToken = accessToken,
            endpointUrl = "$SHEETS_API_BASE/$spreadsheetId/values/${range.urlEncode()}:clear",
            payload = JSONObject()
        )
    }

    private fun sheetPayload(title: String): JSONObject {
        return JSONObject().put("properties", JSONObject().put("title", title))
    }

    private fun List<TransactionEntity>.toExpenseRows(): List<List<Any?>> {
        return listOf(EXPENSE_HEADERS) + map { transaction ->
            listOf(
                transaction.id,
                transaction.sender,
                transaction.body,
                transaction.amount,
                transaction.merchant,
                transaction.category,
                transaction.timestamp,
                transaction.isParsed
            )
        }
    }

    private fun List<TransactionEntity>.toCategoryRows(): List<List<Any?>> {
        val categoryRows = groupBy { it.category ?: "Uncategorized" }
            .map { (category, transactions) ->
                listOf(category, transactions.sumOf { it.amount ?: 0.0 })
            }
            .sortedByDescending { it[1] as Double }

        return listOf(listOf("Category", "Total")) + categoryRows
    }

    private fun List<TransactionEntity>.toMonthlySummaryRows(): List<List<Any?>> {
        val monthlyRows = groupBy { it.monthKey() }
            .map { (month, transactions) ->
                listOf(month, transactions.sumOf { it.amount ?: 0.0 }, transactions.size)
            }
            .sortedByDescending { it[0].toString() }

        return listOf(listOf("Month", "Total", "Transactions")) + monthlyRows
    }

    private fun TransactionEntity.monthKey(): String {
        return java.text.SimpleDateFormat("yyyy-MM", java.util.Locale.US)
            .format(java.util.Date(timestamp))
    }

    private fun JSONArray.toTransactionEntity(): TransactionEntity {
        return TransactionEntity(
            id = optString(0).toIntOrNull() ?: 0,
            sender = optString(1),
            body = optString(2),
            amount = optString(3).toDoubleOrNull(),
            merchant = optNullableString(4),
            category = optNullableString(5),
            timestamp = optString(6).toLongOrNull() ?: System.currentTimeMillis(),
            isParsed = optString(7).toBooleanStrictOrNull() ?: true
        )
    }

    private fun JSONArray.optNullableString(index: Int): String? {
        return optString(index).takeIf { it.isNotBlank() }
    }

    private fun List<List<Any?>>.toJsonArray(): JSONArray {
        val outer = JSONArray()
        forEach { row ->
            val inner = JSONArray()
            row.forEach { value ->
                inner.put(value ?: "")
            }
            outer.put(inner)
        }
        return outer
    }

    private fun getJson(accessToken: String, endpointUrl: String): JSONObject {
        val connection = openConnection(accessToken, endpointUrl, "GET")
        return connection.useResponse { JSONObject(it) }
    }

    private fun postJson(accessToken: String, endpointUrl: String, payload: JSONObject): JSONObject {
        return sendJson(accessToken, endpointUrl, "POST", payload)
    }

    private fun sendJson(
        accessToken: String,
        endpointUrl: String,
        method: String,
        payload: JSONObject
    ): JSONObject {
        val connection = openConnection(accessToken, endpointUrl, method)
        connection.doOutput = true
        OutputStreamWriter(connection.outputStream).use { writer ->
            writer.write(payload.toString())
        }
        return connection.useResponse { JSONObject(it) }
    }

    private fun openConnection(
        accessToken: String,
        endpointUrl: String,
        method: String
    ): HttpURLConnection {
        return (URL(endpointUrl).openConnection() as HttpURLConnection).apply {
            requestMethod = method
            connectTimeout = 15000
            readTimeout = 15000
            setRequestProperty("Authorization", "Bearer $accessToken")
            setRequestProperty("Content-Type", "application/json")
            setRequestProperty("Accept", "application/json")
        }
    }

    private fun <T> HttpURLConnection.useResponse(parser: (String) -> T): T {
        return try {
            val stream = if (responseCode in 200..299) inputStream else errorStream
            val body = stream?.let {
                BufferedReader(InputStreamReader(it)).use { reader -> reader.readText() }
            }.orEmpty()

            if (responseCode !in 200..299) {
                throw IllegalStateException("Google Sheets API failed: HTTP $responseCode $body")
            }

            parser(body.ifBlank { "{}" })
        } finally {
            disconnect()
        }
    }

    private fun saveConnection(spreadsheetId: String) {
        preferences.edit()
            .putString(KEY_SPREADSHEET_ID, spreadsheetId)
            .apply()
    }

    private fun saveLastSyncedAt() {
        preferences.edit()
            .putLong(KEY_LAST_SYNCED_AT, System.currentTimeMillis())
            .apply()
    }

    private fun String.urlEncode(): String {
        return java.net.URLEncoder.encode(this, "UTF-8").replace("+", "%20")
    }

    private companion object {
        const val PREFERENCES_NAME = "muneemji_sheets"
        const val KEY_SPREADSHEET_ID = "spreadsheet_id"
        const val KEY_LAST_SYNCED_AT = "last_synced_at"
        const val SPREADSHEET_TITLE = "MuneemJi Expenses"
        const val EXPENSES_SHEET = "Expenses"
        const val CATEGORIES_SHEET = "Categories"
        const val MONTHLY_SUMMARY_SHEET = "Monthly Summary"
        const val SHEETS_API_BASE = "https://sheets.googleapis.com/v4/spreadsheets"

        val EXPENSE_HEADERS = listOf(
            "id",
            "sender",
            "body",
            "amount",
            "merchant",
            "category",
            "timestamp",
            "isParsed"
        )
    }
}
