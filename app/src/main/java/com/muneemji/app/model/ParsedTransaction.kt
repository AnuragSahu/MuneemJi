package com.muneemji.app.model

data class ParsedTransaction(
    val amount: Double,
    val merchant: String,
    val transactionType: TransactionType,
    val rawText: String,
    val timestamp: Long,
    val category: String = "Other"
)

enum class TransactionType {
    DEBIT, CREDIT, UNKNOWN
}
