package com.muneemji.app.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a\u001e\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0007\u001a\b\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0012\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u0016\u0010\u000b\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0016\u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0012H\u0007\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u0015H\u0002\u00a8\u0006\u0016"}, d2 = {"EmptyScreen", "", "ErrorScreen", "message", "", "onRetry", "Lkotlin/Function0;", "LoadingScreen", "MainScreen", "viewModel", "Lcom/muneemji/app/ui/MainViewModel;", "PermissionScreen", "onRequestPermission", "TransactionCard", "transaction", "Lcom/muneemji/app/db/TransactionEntity;", "TransactionList", "transactions", "", "findActivity", "Landroid/app/Activity;", "Landroid/content/Context;", "app_debug"})
public final class MainScreenKt {
    
    @androidx.compose.runtime.Composable()
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void MainScreen(@org.jetbrains.annotations.NotNull()
    com.muneemji.app.ui.MainViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PermissionScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRequestPermission) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LoadingScreen() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyScreen() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ErrorScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRetry) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TransactionList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.muneemji.app.db.TransactionEntity> transactions) {
    }
    
    @androidx.compose.runtime.Composable()
    @kotlin.OptIn(markerClass = {androidx.compose.material.ExperimentalMaterialApi.class})
    public static final void TransactionCard(@org.jetbrains.annotations.NotNull()
    com.muneemji.app.db.TransactionEntity transaction) {
    }
    
    private static final android.app.Activity findActivity(android.content.Context $this$findActivity) {
        return null;
    }
}