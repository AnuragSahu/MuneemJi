package com.muneemji.app.di

import android.content.Context
import androidx.room.Room
import com.muneemji.app.db.AppDatabase
import com.muneemji.app.db.TransactionDao
import com.muneemji.app.parser.TransactionParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "muneemji.db"
        ).build()
    }

    @Provides
    fun provideTransactionDao(database: AppDatabase): TransactionDao {
        return database.transactionDao()
    }

    @Provides
    @Singleton
    fun provideTransactionParser(): TransactionParser {
        return TransactionParser()
    }
}
