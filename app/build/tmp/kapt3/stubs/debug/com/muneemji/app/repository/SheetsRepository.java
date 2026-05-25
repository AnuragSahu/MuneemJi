package com.muneemji.app.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 92\u00020\u0001:\u00019B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J6\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0014\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00110\u0011H\u0002J \u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\rH\u0002J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\b\u0010\u0019\u001a\u0004\u0018\u00010\u0014J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\rH\u0002J\u0019\u0010\u001d\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J \u0010\u001e\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010 \u001a\u00020\rH\u0002J \u0010!\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u001bH\u0002J6\u0010#\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0014\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00110\u0011H\u0002J\u0010\u0010$\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010%\u001a\u00020\u000bH\u0002J(\u0010&\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u001bH\u0002J\u0010\u0010\'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\rH\u0002J\f\u0010)\u001a\u00020\r*\u00020*H\u0002J\u0016\u0010+\u001a\u0004\u0018\u00010\r*\u00020,2\u0006\u0010-\u001a\u00020\u0018H\u0002J \u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00110\u0011*\b\u0012\u0004\u0012\u00020*0\u0011H\u0002J \u0010/\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00110\u0011*\b\u0012\u0004\u0012\u00020*0\u0011H\u0002J\u001a\u00100\u001a\u00020,*\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00110\u0011H\u0002J \u00101\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00110\u0011*\b\u0012\u0004\u0012\u00020*0\u0011H\u0002J\f\u00102\u001a\u00020**\u00020,H\u0002J\f\u00103\u001a\u00020\r*\u00020\rH\u0002J+\u00104\u001a\u0002H5\"\u0004\b\u0000\u00105*\u00020\u001f2\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H507H\u0002\u00a2\u0006\u0002\u00108R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006:"}, d2 = {"Lcom/muneemji/app/repository/SheetsRepository;", "", "context", "Landroid/content/Context;", "transactionDao", "Lcom/muneemji/app/db/TransactionDao;", "(Landroid/content/Context;Lcom/muneemji/app/db/TransactionDao;)V", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "appendValues", "", "accessToken", "", "spreadsheetId", "range", "values", "", "clearValues", "connect", "Lcom/muneemji/app/repository/SheetsConnection;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSpreadsheet", "exportTransactions", "", "getConnection", "getJson", "Lorg/json/JSONObject;", "endpointUrl", "importTransactions", "openConnection", "Ljava/net/HttpURLConnection;", "method", "postJson", "payload", "replaceValues", "saveConnection", "saveLastSyncedAt", "sendJson", "sheetPayload", "title", "monthKey", "Lcom/muneemji/app/db/TransactionEntity;", "optNullableString", "Lorg/json/JSONArray;", "index", "toCategoryRows", "toExpenseRows", "toJsonArray", "toMonthlySummaryRows", "toTransactionEntity", "urlEncode", "useResponse", "T", "parser", "Lkotlin/Function1;", "(Ljava/net/HttpURLConnection;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Companion", "app_debug"})
@javax.inject.Singleton()
public final class SheetsRepository {
    private final com.muneemji.app.db.TransactionDao transactionDao = null;
    private final android.content.SharedPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.muneemji.app.repository.SheetsRepository.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String PREFERENCES_NAME = "muneemji_sheets";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String KEY_SPREADSHEET_ID = "spreadsheet_id";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String KEY_LAST_SYNCED_AT = "last_synced_at";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String SPREADSHEET_TITLE = "MuneemJi Expenses";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String EXPENSES_SHEET = "Expenses";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String CATEGORIES_SHEET = "Categories";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String MONTHLY_SUMMARY_SHEET = "Monthly Summary";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String SHEETS_API_BASE = "https://sheets.googleapis.com/v4/spreadsheets";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    private static final java.util.List<java.lang.String> EXPENSE_HEADERS = null;
    
    @javax.inject.Inject()
    public SheetsRepository(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.muneemji.app.db.TransactionDao transactionDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.muneemji.app.repository.SheetsConnection getConnection() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object connect(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.muneemji.app.repository.SheetsConnection> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportTransactions(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importTransactions(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
    
    private final com.muneemji.app.repository.SheetsConnection createSpreadsheet(java.lang.String accessToken) {
        return null;
    }
    
    private final void replaceValues(java.lang.String accessToken, java.lang.String spreadsheetId, java.lang.String range, java.util.List<? extends java.util.List<? extends java.lang.Object>> values) {
    }
    
    private final void appendValues(java.lang.String accessToken, java.lang.String spreadsheetId, java.lang.String range, java.util.List<? extends java.util.List<? extends java.lang.Object>> values) {
    }
    
    private final void clearValues(java.lang.String accessToken, java.lang.String spreadsheetId, java.lang.String range) {
    }
    
    private final org.json.JSONObject sheetPayload(java.lang.String title) {
        return null;
    }
    
    private final java.util.List<java.util.List<java.lang.Object>> toExpenseRows(java.util.List<com.muneemji.app.db.TransactionEntity> $this$toExpenseRows) {
        return null;
    }
    
    private final java.util.List<java.util.List<java.lang.Object>> toCategoryRows(java.util.List<com.muneemji.app.db.TransactionEntity> $this$toCategoryRows) {
        return null;
    }
    
    private final java.util.List<java.util.List<java.lang.Object>> toMonthlySummaryRows(java.util.List<com.muneemji.app.db.TransactionEntity> $this$toMonthlySummaryRows) {
        return null;
    }
    
    private final java.lang.String monthKey(com.muneemji.app.db.TransactionEntity $this$monthKey) {
        return null;
    }
    
    private final com.muneemji.app.db.TransactionEntity toTransactionEntity(org.json.JSONArray $this$toTransactionEntity) {
        return null;
    }
    
    private final java.lang.String optNullableString(org.json.JSONArray $this$optNullableString, int index) {
        return null;
    }
    
    private final org.json.JSONArray toJsonArray(java.util.List<? extends java.util.List<? extends java.lang.Object>> $this$toJsonArray) {
        return null;
    }
    
    private final org.json.JSONObject getJson(java.lang.String accessToken, java.lang.String endpointUrl) {
        return null;
    }
    
    private final org.json.JSONObject postJson(java.lang.String accessToken, java.lang.String endpointUrl, org.json.JSONObject payload) {
        return null;
    }
    
    private final org.json.JSONObject sendJson(java.lang.String accessToken, java.lang.String endpointUrl, java.lang.String method, org.json.JSONObject payload) {
        return null;
    }
    
    private final java.net.HttpURLConnection openConnection(java.lang.String accessToken, java.lang.String endpointUrl, java.lang.String method) {
        return null;
    }
    
    private final <T extends java.lang.Object>T useResponse(java.net.HttpURLConnection $this$useResponse, kotlin.jvm.functions.Function1<? super java.lang.String, ? extends T> parser) {
        return null;
    }
    
    private final void saveConnection(java.lang.String spreadsheetId) {
    }
    
    private final void saveLastSyncedAt() {
    }
    
    private final java.lang.String urlEncode(java.lang.String $this$urlEncode) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/muneemji/app/repository/SheetsRepository$Companion;", "", "()V", "CATEGORIES_SHEET", "", "EXPENSES_SHEET", "EXPENSE_HEADERS", "", "getEXPENSE_HEADERS", "()Ljava/util/List;", "KEY_LAST_SYNCED_AT", "KEY_SPREADSHEET_ID", "MONTHLY_SUMMARY_SHEET", "PREFERENCES_NAME", "SHEETS_API_BASE", "SPREADSHEET_TITLE", "app_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getEXPENSE_HEADERS() {
            return null;
        }
    }
}