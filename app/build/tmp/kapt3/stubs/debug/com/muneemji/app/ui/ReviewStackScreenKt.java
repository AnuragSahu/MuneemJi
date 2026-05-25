package com.muneemji.app.ui;

import java.lang.System;

@kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a<\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000fH\u0003\u001a\\\u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\t26\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0016\u00a2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u00010\u0015H\u0007\u001a<\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00052\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u00a8\u0006\u001f"}, d2 = {"CategoryOption", "", "category", "", "selected", "", "modifier", "Landroidx/compose/ui/Modifier;", "onClick", "Lkotlin/Function0;", "CategoryPickerCard", "transaction", "Lcom/muneemji/app/ui/MockTransaction;", "onDismiss", "onCategorySelected", "Lkotlin/Function1;", "ReviewStackScreen", "transactions", "", "Lcom/muneemji/app/db/TransactionEntity;", "onBack", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "transactionId", "SwipeableCard", "index", "isTop", "onSwipedLeft", "onSwipedRight", "app_debug"})
public final class ReviewStackScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void ReviewStackScreen(@org.jetbrains.annotations.NotNull()
    java.util.List<com.muneemji.app.db.TransactionEntity> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.String, kotlin.Unit> onCategorySelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CategoryPickerCard(com.muneemji.app.ui.MockTransaction transaction, androidx.compose.ui.Modifier modifier, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCategorySelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CategoryOption(java.lang.String category, boolean selected, androidx.compose.ui.Modifier modifier, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SwipeableCard(@org.jetbrains.annotations.NotNull()
    com.muneemji.app.ui.MockTransaction transaction, int index, boolean isTop, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSwipedLeft, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSwipedRight) {
    }
}