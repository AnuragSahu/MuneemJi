package com.muneemji.app.ui;

import java.lang.System;

@kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0003\u001a\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0003\u001a\u0010\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0003\u001a\u0016\u0010\f\u001a\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\u0016\u0010\u000f\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u001al\u0010\u0011\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001bH\u0003\u001a \u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0003\u001a\u001e\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020!2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u001a\u0016\u0010$\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003H\u0003\u001a\u0010\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0013H\u0003\u001a\u0010\u0010\'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u001bH\u0003\u001a:\u0010)\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u001a\"\u0010,\u001a\u00020\u00012\u0006\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u001b2\b\b\u0002\u0010/\u001a\u000200H\u0003\u001a\u0010\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u000203H\u0002\u001a\u0010\u00104\u001a\u00020\u001b2\u0006\u00102\u001a\u000203H\u0002\u001a\u0010\u00105\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\u001eH\u0002\u001a\f\u00107\u001a\u00020\u0015*\u00020\u0013H\u0002\u00a8\u00068"}, d2 = {"CategoryBreakdown", "", "categorySpend", "", "Lcom/muneemji/app/ui/CategorySpend;", "CategorySpendRow", "category", "progress", "", "ConnectedSheetSummary", "sheetsUiState", "Lcom/muneemji/app/ui/SheetsUiState;", "DashboardBottomNav", "onNavigateToReview", "Lkotlin/Function0;", "DashboardHeader", "onSync", "DashboardScreen", "transactions", "Lcom/muneemji/app/db/TransactionEntity;", "isSyncing", "", "onConnectGoogle", "onExportToSheets", "onImportFromSheets", "EmptyDashboardCard", "message", "", "MonthlySpendCard", "monthlySpend", "", "totalSpend", "transactionCount", "", "PendingReviewCard", "reviewCount", "RecentActivity", "RecentActivityItem", "transaction", "SectionTitle", "title", "SheetsIntegrationCard", "onExport", "onImport", "SummaryTile", "label", "value", "modifier", "Landroidx/compose/ui/Modifier;", "formatDate", "timestamp", "", "formatFullDate", "formatRupees", "amount", "isInCurrentMonth", "app_debug"})
public final class DashboardScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    java.util.List<com.muneemji.app.db.TransactionEntity> transactions, boolean isSyncing, @org.jetbrains.annotations.NotNull()
    com.muneemji.app.ui.SheetsUiState sheetsUiState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSync, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onConnectGoogle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onExportToSheets, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onImportFromSheets, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToReview) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DashboardHeader(kotlin.jvm.functions.Function0<kotlin.Unit> onSync) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MonthlySpendCard(double monthlySpend, double totalSpend, int transactionCount) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SummaryTile(java.lang.String label, java.lang.String value, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PendingReviewCard(int reviewCount, kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToReview) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SheetsIntegrationCard(com.muneemji.app.ui.SheetsUiState sheetsUiState, kotlin.jvm.functions.Function0<kotlin.Unit> onConnectGoogle, kotlin.jvm.functions.Function0<kotlin.Unit> onExport, kotlin.jvm.functions.Function0<kotlin.Unit> onImport) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ConnectedSheetSummary(com.muneemji.app.ui.SheetsUiState sheetsUiState) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CategoryBreakdown(java.util.List<com.muneemji.app.ui.CategorySpend> categorySpend) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CategorySpendRow(com.muneemji.app.ui.CategorySpend category, float progress) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RecentActivity(java.util.List<com.muneemji.app.db.TransactionEntity> transactions) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RecentActivityItem(com.muneemji.app.db.TransactionEntity transaction) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SectionTitle(java.lang.String title) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyDashboardCard(java.lang.String message) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DashboardBottomNav(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToReview) {
    }
    
    private static final boolean isInCurrentMonth(com.muneemji.app.db.TransactionEntity $this$isInCurrentMonth) {
        return false;
    }
    
    private static final java.lang.String formatRupees(double amount) {
        return null;
    }
    
    private static final java.lang.String formatDate(long timestamp) {
        return null;
    }
    
    private static final java.lang.String formatFullDate(long timestamp) {
        return null;
    }
}