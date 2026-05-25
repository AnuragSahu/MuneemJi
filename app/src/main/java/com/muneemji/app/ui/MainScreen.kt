package com.muneemji.app.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.muneemji.app.db.TransactionEntity
import com.google.android.gms.auth.api.identity.AuthorizationRequest
import com.google.android.gms.auth.api.identity.AuthorizationResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.Scope
import java.text.SimpleDateFormat
import java.util.*

private enum class SheetsAction {
    Connect,
    Export,
    Import
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val isSyncing by viewModel.isSyncing.collectAsState()
    val hasCompletedOnboarding by viewModel.hasCompletedOnboarding.collectAsState()
    val sheetsUiState by viewModel.sheetsUiState.collectAsState()
    val context = LocalContext.current
    val activity = context.findActivity()
    var showReview by rememberSaveable { mutableStateOf(false) }
    var pendingSheetsAction by rememberSaveable { mutableStateOf<SheetsAction?>(null) }
    
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val readGranted = permissions[Manifest.permission.READ_SMS] == true
        val receiveGranted = permissions[Manifest.permission.RECEIVE_SMS] == true
        
        if (readGranted && receiveGranted) {
            viewModel.setPermissionGranted()
        }
    }

    LaunchedEffect(Unit) {
        val hasRead = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
        val hasReceive = ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
        
        if (hasRead && hasReceive) {
            viewModel.setPermissionGranted()
        }
    }

    fun handleSheetsAuthorization(
        action: SheetsAction,
        authorizationResult: AuthorizationResult
    ) {
        val accessToken = authorizationResult.accessToken
        if (accessToken.isNullOrBlank()) {
            viewModel.setSheetsAuthorizationError("Google did not return an access token. Please try again.")
            return
        }

        when (action) {
            SheetsAction.Connect -> viewModel.connectGoogleSheets(accessToken)
            SheetsAction.Export -> viewModel.exportToSheets(accessToken)
            SheetsAction.Import -> viewModel.importFromSheets(accessToken)
        }
    }

    val sheetsAuthorizationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        val action = pendingSheetsAction
        pendingSheetsAction = null

        if (action == null) return@rememberLauncherForActivityResult

        try {
            val authorizationResult = Identity.getAuthorizationClient(context)
                .getAuthorizationResultFromIntent(result.data)
            handleSheetsAuthorization(action, authorizationResult)
        } catch (e: Exception) {
            viewModel.setSheetsAuthorizationError(e.message ?: "Google authorization was cancelled.")
        }
    }

    fun requestSheetsAuthorization(action: SheetsAction) {
        val hostActivity = activity
        if (hostActivity == null) {
            viewModel.setSheetsAuthorizationError("Unable to start Google authorization from this screen.")
            return
        }

        pendingSheetsAction = action

        val authorizationRequest = AuthorizationRequest.builder()
            .setRequestedScopes(
                listOf(
                    Scope("https://www.googleapis.com/auth/spreadsheets"),
                    Scope("https://www.googleapis.com/auth/drive.file")
                )
            )
            .build()

        Identity.getAuthorizationClient(hostActivity)
            .authorize(authorizationRequest)
            .addOnSuccessListener { authorizationResult ->
                if (authorizationResult.hasResolution()) {
                    val pendingIntent = authorizationResult.pendingIntent
                    if (pendingIntent == null) {
                        pendingSheetsAction = null
                        viewModel.setSheetsAuthorizationError("Google authorization needs consent but returned no prompt.")
                    } else {
                        sheetsAuthorizationLauncher.launch(
                            IntentSenderRequest.Builder(pendingIntent.intentSender).build()
                        )
                    }
                } else {
                    pendingSheetsAction = null
                    handleSheetsAuthorization(action, authorizationResult)
                }
            }
            .addOnFailureListener { error ->
                pendingSheetsAction = null
                viewModel.setSheetsAuthorizationError(error.message ?: "Unable to start Google authorization.")
            }
    }

    if (!hasCompletedOnboarding) {
        OnboardingScreen(onFinished = viewModel::completeOnboarding)
        return
    }

    when (val state = uiState) {
        is UiState.Success -> {
            if (showReview) {
                ReviewStackScreen(
                    transactions = state.transactions,
                    onBack = { showReview = false },
                    onCategorySelected = viewModel::updateCategory
                )
            } else {
                DashboardScreen(
                    transactions = state.transactions,
                    isSyncing = isSyncing,
                    sheetsUiState = sheetsUiState,
                    onSync = { viewModel.syncMessages() },
                    onConnectGoogle = { requestSheetsAuthorization(SheetsAction.Connect) },
                    onExportToSheets = { requestSheetsAuthorization(SheetsAction.Export) },
                    onImportFromSheets = { requestSheetsAuthorization(SheetsAction.Import) },
                    onNavigateToReview = { showReview = true }
                )
            }
        }

        is UiState.Empty -> {
            if (showReview) {
                ReviewStackScreen(
                    transactions = emptyList(),
                    onBack = { showReview = false },
                    onCategorySelected = viewModel::updateCategory
                )
            } else {
                DashboardScreen(
                    transactions = emptyList(),
                    isSyncing = isSyncing,
                    sheetsUiState = sheetsUiState,
                    onSync = { viewModel.syncMessages() },
                    onConnectGoogle = { requestSheetsAuthorization(SheetsAction.Connect) },
                    onExportToSheets = { requestSheetsAuthorization(SheetsAction.Export) },
                    onImportFromSheets = { requestSheetsAuthorization(SheetsAction.Import) },
                    onNavigateToReview = { showReview = true }
                )
            }
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("MuneemJi", fontWeight = FontWeight.Bold) },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    when (val state = uiState) {
                        is UiState.PermissionRequired -> {
                            PermissionScreen {
                                permissionLauncher.launch(
                                    arrayOf(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS)
                                )
                            }
                        }

                        is UiState.Loading -> {
                            LoadingScreen()
                        }

                        is UiState.Error -> {
                            ErrorScreen(state.message) { viewModel.syncMessages() }
                        }

                        is UiState.Success, is UiState.Empty -> Unit
                    }
                }
            }
        }
    }
}

@Composable
fun PermissionScreen(onRequestPermission: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "SMS Permission Required",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "MuneemJi needs access to read your SMS messages to track your expenses securely on your device.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onRequestPermission) {
            Text("Grant Permission")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("No transactions found.", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Error: $message", color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun TransactionList(transactions: List<TransactionEntity>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(transactions, key = { it.id }) { tx ->
            TransactionCard(tx)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransactionCard(transaction: TransactionEntity) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault())
    val dateString = dateFormat.format(Date(transaction.timestamp))
    
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                // In a real app, you would call viewModel.deleteTransaction(transaction) here
                true
            } else {
                false
            }
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.DismissedToStart -> Color.Red
                    else -> Color.Transparent
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color, RoundedCornerShape(16.dp))
                    .padding(end = 24.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (dismissState.targetValue == DismissValue.DismissedToStart) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                }
            }
        },
        dismissContent = {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = transaction.merchant ?: "Unknown",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "₹${transaction.amount}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = dateString,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                transaction.category?.let { cat ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = cat,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = transaction.body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
        }
    )
}

private fun Context.findActivity(): Activity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) return currentContext
        currentContext = currentContext.baseContext
    }
    return null
}
