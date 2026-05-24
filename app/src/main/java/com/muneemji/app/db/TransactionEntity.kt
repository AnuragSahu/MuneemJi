package com.muneemji.app.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sender: String,
    val body: String,
    val amount: Double?,
    val merchant: String?,
    val category: String?,
    val timestamp: Long,
    val isParsed: Boolean
)
