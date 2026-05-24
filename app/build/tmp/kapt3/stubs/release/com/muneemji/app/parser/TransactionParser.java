package com.muneemji.app.parser;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0002J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002J \u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/muneemji/app/parser/TransactionParser;", "", "()V", "amountRegex", "Lkotlin/text/Regex;", "creditKeywords", "", "", "debitKeywords", "extractMerchant", "body", "guessCategory", "merchant", "parse", "Lcom/muneemji/app/model/ParsedTransaction;", "smsBody", "timestamp", "", "sender", "app_release"})
public final class TransactionParser {
    private final java.util.List<java.lang.String> debitKeywords = null;
    private final java.util.List<java.lang.String> creditKeywords = null;
    private final kotlin.text.Regex amountRegex = null;
    
    public TransactionParser() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.muneemji.app.model.ParsedTransaction parse(@org.jetbrains.annotations.NotNull()
    java.lang.String smsBody, long timestamp, @org.jetbrains.annotations.NotNull()
    java.lang.String sender) {
        return null;
    }
    
    private final java.lang.String extractMerchant(java.lang.String body) {
        return null;
    }
    
    private final java.lang.String guessCategory(java.lang.String merchant) {
        return null;
    }
}