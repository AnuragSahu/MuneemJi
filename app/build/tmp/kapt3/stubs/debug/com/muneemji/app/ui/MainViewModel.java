package com.muneemji.app.ui;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001bJ\u000e\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u001eJ\u0006\u0010$\u001a\u00020\u001bJ\u0016\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u001eJ\u000e\u0010)\u001a\u00020\u000e*\u0004\u0018\u00010*H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014\u00a8\u0006+"}, d2 = {"Lcom/muneemji/app/ui/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/muneemji/app/repository/SmsRepository;", "onboardingRepository", "Lcom/muneemji/app/repository/OnboardingRepository;", "sheetsRepository", "Lcom/muneemji/app/repository/SheetsRepository;", "(Lcom/muneemji/app/repository/SmsRepository;Lcom/muneemji/app/repository/OnboardingRepository;Lcom/muneemji/app/repository/SheetsRepository;)V", "_hasCompletedOnboarding", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isSyncing", "_sheetsUiState", "Lcom/muneemji/app/ui/SheetsUiState;", "_uiState", "Lcom/muneemji/app/ui/UiState;", "hasCompletedOnboarding", "Lkotlinx/coroutines/flow/StateFlow;", "getHasCompletedOnboarding", "()Lkotlinx/coroutines/flow/StateFlow;", "isSyncing", "sheetsUiState", "getSheetsUiState", "uiState", "getUiState", "completeOnboarding", "", "connectGoogleSheets", "accessToken", "", "exportToSheets", "importFromSheets", "setPermissionGranted", "setSheetsAuthorizationError", "message", "syncMessages", "updateCategory", "transactionId", "", "category", "toUiState", "Lcom/muneemji/app/repository/SheetsConnection;", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    private final com.muneemji.app.repository.SmsRepository repository = null;
    private final com.muneemji.app.repository.OnboardingRepository onboardingRepository = null;
    private final com.muneemji.app.repository.SheetsRepository sheetsRepository = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.muneemji.app.ui.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.muneemji.app.ui.UiState> uiState = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isSyncing = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSyncing = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _hasCompletedOnboarding = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> hasCompletedOnboarding = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.muneemji.app.ui.SheetsUiState> _sheetsUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.muneemji.app.ui.SheetsUiState> sheetsUiState = null;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.muneemji.app.repository.SmsRepository repository, @org.jetbrains.annotations.NotNull()
    com.muneemji.app.repository.OnboardingRepository onboardingRepository, @org.jetbrains.annotations.NotNull()
    com.muneemji.app.repository.SheetsRepository sheetsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.muneemji.app.ui.UiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSyncing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getHasCompletedOnboarding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.muneemji.app.ui.SheetsUiState> getSheetsUiState() {
        return null;
    }
    
    public final void setPermissionGranted() {
    }
    
    public final void syncMessages() {
    }
    
    public final void updateCategory(int transactionId, @org.jetbrains.annotations.NotNull()
    java.lang.String category) {
    }
    
    public final void completeOnboarding() {
    }
    
    public final void connectGoogleSheets(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken) {
    }
    
    public final void exportToSheets(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken) {
    }
    
    public final void importFromSheets(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken) {
    }
    
    public final void setSheetsAuthorizationError(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    private final com.muneemji.app.ui.SheetsUiState toUiState(com.muneemji.app.repository.SheetsConnection $this$toUiState) {
        return null;
    }
}