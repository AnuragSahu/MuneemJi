package com.muneemji.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muneemji.app.db.TransactionEntity
import com.muneemji.app.repository.OnboardingRepository
import com.muneemji.app.repository.SheetsRepository
import com.muneemji.app.repository.SmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    object PermissionRequired : UiState()
    object Loading : UiState()
    object Empty : UiState()
    data class Success(val transactions: List<TransactionEntity>) : UiState()
    data class Error(val message: String) : UiState()
}

data class SheetsUiState(
    val spreadsheetId: String? = null,
    val lastSyncedAt: Long = 0L,
    val isSyncing: Boolean = false,
    val isConnected: Boolean = false,
    val statusMessage: String? = null,
    val errorMessage: String? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SmsRepository,
    private val onboardingRepository: OnboardingRepository,
    private val sheetsRepository: SheetsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.PermissionRequired)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private val _hasCompletedOnboarding = MutableStateFlow(onboardingRepository.hasCompletedOnboarding())
    val hasCompletedOnboarding: StateFlow<Boolean> = _hasCompletedOnboarding.asStateFlow()

    private val _sheetsUiState = MutableStateFlow(sheetsRepository.getConnection().toUiState())
    val sheetsUiState: StateFlow<SheetsUiState> = _sheetsUiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.transactions
                .catch { e -> _uiState.value = UiState.Error(e.message ?: "Unknown error") }
                .collect { txList ->
                    if (_uiState.value !is UiState.PermissionRequired) {
                        if (txList.isEmpty()) {
                            _uiState.value = UiState.Empty
                        } else {
                            _uiState.value = UiState.Success(txList)
                        }
                    }
                }
        }
    }

    fun setPermissionGranted() {
        if (_uiState.value is UiState.PermissionRequired) {
            syncMessages()
        }
    }

    fun syncMessages() {
        viewModelScope.launch {
            _isSyncing.value = true
            try {
                if (_uiState.value !is UiState.Success) {
                    _uiState.value = UiState.Loading
                }
                repository.syncSmsMessages()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error syncing messages")
            } finally {
                _isSyncing.value = false
            }
        }
    }

    fun updateCategory(transactionId: Int, category: String) {
        viewModelScope.launch {
            try {
                repository.updateCategory(transactionId, category)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error updating category")
            }
        }
    }

    fun completeOnboarding() {
        onboardingRepository.markOnboardingComplete()
        _hasCompletedOnboarding.value = true
    }

    fun connectGoogleSheets(accessToken: String) {
        viewModelScope.launch {
            _sheetsUiState.value = _sheetsUiState.value.copy(
                isSyncing = true,
                statusMessage = null,
                errorMessage = null
            )
            try {
                sheetsRepository.connect(accessToken)
                val count = sheetsRepository.exportTransactions(accessToken)
                val connection = sheetsRepository.getConnection()
                _sheetsUiState.value = _sheetsUiState.value.copy(
                    spreadsheetId = connection?.spreadsheetId,
                    lastSyncedAt = connection?.lastSyncedAt ?: System.currentTimeMillis(),
                    isConnected = true,
                    isSyncing = false,
                    statusMessage = "Google Sheets is connected and synced $count transactions.",
                    errorMessage = null
                )
            } catch (e: Exception) {
                _sheetsUiState.value = _sheetsUiState.value.copy(
                    isSyncing = false,
                    errorMessage = e.message ?: "Unable to connect Google Sheets."
                )
            }
        }
    }

    fun exportToSheets(accessToken: String) {
        viewModelScope.launch {
            _sheetsUiState.value = _sheetsUiState.value.copy(
                isSyncing = true,
                statusMessage = null,
                errorMessage = null
            )
            try {
                val count = sheetsRepository.exportTransactions(accessToken)
                val connection = sheetsRepository.getConnection()
                _sheetsUiState.value = _sheetsUiState.value.copy(
                    spreadsheetId = connection?.spreadsheetId,
                    lastSyncedAt = connection?.lastSyncedAt ?: System.currentTimeMillis(),
                    isConnected = true,
                    isSyncing = false,
                    statusMessage = "Exported $count transactions to Google Sheets.",
                    errorMessage = null
                )
            } catch (e: Exception) {
                _sheetsUiState.value = _sheetsUiState.value.copy(
                    isSyncing = false,
                    errorMessage = e.message ?: "Unable to export to Google Sheets."
                )
            }
        }
    }

    fun importFromSheets(accessToken: String) {
        viewModelScope.launch {
            _sheetsUiState.value = _sheetsUiState.value.copy(
                isSyncing = true,
                statusMessage = null,
                errorMessage = null
            )
            try {
                val count = sheetsRepository.importTransactions(accessToken)
                val connection = sheetsRepository.getConnection()
                _sheetsUiState.value = _sheetsUiState.value.copy(
                    spreadsheetId = connection?.spreadsheetId,
                    lastSyncedAt = connection?.lastSyncedAt ?: System.currentTimeMillis(),
                    isConnected = true,
                    isSyncing = false,
                    statusMessage = "Imported $count transactions from Google Sheets.",
                    errorMessage = null
                )
            } catch (e: Exception) {
                _sheetsUiState.value = _sheetsUiState.value.copy(
                    isSyncing = false,
                    errorMessage = e.message ?: "Unable to import from Google Sheets."
                )
            }
        }
    }

    fun setSheetsAuthorizationError(message: String) {
        _sheetsUiState.value = _sheetsUiState.value.copy(
            isSyncing = false,
            errorMessage = message,
            statusMessage = null
        )
    }

    private fun com.muneemji.app.repository.SheetsConnection?.toUiState(): SheetsUiState {
        return SheetsUiState(
            spreadsheetId = this?.spreadsheetId,
            lastSyncedAt = this?.lastSyncedAt ?: 0L,
            isConnected = this != null
        )
    }
}
