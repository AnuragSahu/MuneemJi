package com.muneemji.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muneemji.app.db.TransactionEntity
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

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SmsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.PermissionRequired)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

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
}
