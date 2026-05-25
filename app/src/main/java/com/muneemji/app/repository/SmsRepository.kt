package com.muneemji.app.repository

import android.content.Context
import android.net.Uri
import android.provider.Telephony
import com.muneemji.app.db.TransactionDao
import com.muneemji.app.db.TransactionEntity
import com.muneemji.app.parser.TransactionParser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val transactionDao: TransactionDao,
    private val parser: TransactionParser
) {
    val transactions: Flow<List<TransactionEntity>> = transactionDao.getAllTransactions()

    suspend fun updateCategory(transactionId: Int, category: String) = withContext(Dispatchers.IO) {
        transactionDao.updateCategory(transactionId, category)
    }

    suspend fun syncSmsMessages() = withContext(Dispatchers.IO) {
        val cursor = context.contentResolver.query(
            Uri.parse("content://sms/inbox"),
            arrayOf("address", "body", "date"),
            null,
            null,
            "date DESC LIMIT 500"
        )

        val newTransactions = mutableListOf<TransactionEntity>()

        cursor?.use {
            val addressIndex = it.getColumnIndexOrThrow("address")
            val bodyIndex = it.getColumnIndexOrThrow("body")
            val dateIndex = it.getColumnIndexOrThrow("date")

            while (it.moveToNext()) {
                val sender = it.getString(addressIndex) ?: ""
                val body = it.getString(bodyIndex) ?: ""
                val date = it.getLong(dateIndex)

                val parsed = parser.parse(body, date, sender)
                
                if (parsed != null) {
                    newTransactions.add(
                        TransactionEntity(
                            sender = sender,
                            body = body,
                            amount = parsed.amount,
                            merchant = parsed.merchant,
                            category = parsed.category,
                            timestamp = date,
                            isParsed = true
                        )
                    )
                }
            }
        }

        if (newTransactions.isNotEmpty()) {
            // First clear old ones for simplicity in this version, or just replace
            // depending on requirements. We'll clear and insert for a fresh sync.
            transactionDao.clearAll()
            transactionDao.insertAll(newTransactions)
        }
    }
}
