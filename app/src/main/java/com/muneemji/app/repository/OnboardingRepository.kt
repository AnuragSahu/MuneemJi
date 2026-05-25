package com.muneemji.app.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun hasCompletedOnboarding(): Boolean {
        return preferences.getBoolean(KEY_ONBOARDING_COMPLETE, false)
    }

    fun markOnboardingComplete() {
        preferences.edit()
            .putBoolean(KEY_ONBOARDING_COMPLETE, true)
            .apply()
    }

    private companion object {
        const val PREFERENCES_NAME = "muneemji_onboarding"
        const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"
    }
}
