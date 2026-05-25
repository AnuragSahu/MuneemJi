package com.muneemji.app.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/muneemji/app/repository/OnboardingRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "hasCompletedOnboarding", "", "markOnboardingComplete", "", "Companion", "app_debug"})
@javax.inject.Singleton()
public final class OnboardingRepository {
    private final android.content.SharedPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.muneemji.app.repository.OnboardingRepository.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String PREFERENCES_NAME = "muneemji_onboarding";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String KEY_ONBOARDING_COMPLETE = "onboarding_complete";
    
    @javax.inject.Inject()
    public OnboardingRepository(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        super();
    }
    
    public final boolean hasCompletedOnboarding() {
        return false;
    }
    
    public final void markOnboardingComplete() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/muneemji/app/repository/OnboardingRepository$Companion;", "", "()V", "KEY_ONBOARDING_COMPLETE", "", "PREFERENCES_NAME", "app_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}