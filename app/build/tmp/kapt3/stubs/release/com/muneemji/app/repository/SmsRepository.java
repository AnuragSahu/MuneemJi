package com.muneemji.app.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010\u000f\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/muneemji/app/repository/SmsRepository;", "", "context", "Landroid/content/Context;", "transactionDao", "Lcom/muneemji/app/db/TransactionDao;", "parser", "Lcom/muneemji/app/parser/TransactionParser;", "(Landroid/content/Context;Lcom/muneemji/app/db/TransactionDao;Lcom/muneemji/app/parser/TransactionParser;)V", "transactions", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/muneemji/app/db/TransactionEntity;", "getTransactions", "()Lkotlinx/coroutines/flow/Flow;", "syncSmsMessages", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
@javax.inject.Singleton()
public final class SmsRepository {
    private final android.content.Context context = null;
    private final com.muneemji.app.db.TransactionDao transactionDao = null;
    private final com.muneemji.app.parser.TransactionParser parser = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.muneemji.app.db.TransactionEntity>> transactions = null;
    
    @javax.inject.Inject()
    public SmsRepository(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.muneemji.app.db.TransactionDao transactionDao, @org.jetbrains.annotations.NotNull()
    com.muneemji.app.parser.TransactionParser parser) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.muneemji.app.db.TransactionEntity>> getTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncSmsMessages(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}