package com.muneemji.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transactions: List<TransactionEntity>)

    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    suspend fun getAllTransactionsOnce(): List<TransactionEntity>

    @Query("UPDATE transactions SET category = :category WHERE id = :transactionId")
    suspend fun updateCategory(transactionId: Int, category: String)

    @Query("DELETE FROM transactions")
    suspend fun clearAll()
}
