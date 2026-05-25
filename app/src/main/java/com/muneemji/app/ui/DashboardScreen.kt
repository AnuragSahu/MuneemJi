@file:OptIn(ExperimentalMaterial3Api::class)

package com.muneemji.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muneemji.app.db.TransactionEntity
import com.muneemji.app.ui.theme.CardIvory
import com.muneemji.app.ui.theme.PrimaryIndigo
import com.muneemji.app.ui.theme.SaffronAccent
import com.muneemji.app.ui.theme.SurfaceVariant
import com.muneemji.app.ui.theme.TextSecondary
import com.muneemji.app.ui.theme.WarmBackground
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

private data class CategorySpend(
    val name: String,
    val amount: Double
)

@Composable
fun DashboardScreen(
    transactions: List<TransactionEntity>,
    isSyncing: Boolean,
    sheetsUiState: SheetsUiState,
    onSync: () -> Unit,
    onConnectGoogle: () -> Unit,
    onExportToSheets: () -> Unit,
    onImportFromSheets: () -> Unit,
    onNavigateToReview: () -> Unit
) {
    val parsedTransactions = transactions.filter { it.amount != null }
    val monthlyTransactions = parsedTransactions.filter { it.isInCurrentMonth() }
    val monthlySpend = monthlyTransactions.sumOf { it.amount ?: 0.0 }
    val totalSpend = parsedTransactions.sumOf { it.amount ?: 0.0 }
    val categorySpend = parsedTransactions
        .groupBy { it.category ?: "Uncategorized" }
        .map { (category, items) ->
            CategorySpend(category, items.sumOf { it.amount ?: 0.0 })
        }
        .sortedByDescending { it.amount }
    val topCategory = categorySpend.firstOrNull()?.name ?: "No category yet"
    val reviewCount = transactions.size

    Scaffold(
        containerColor = WarmBackground,
        bottomBar = { DashboardBottomNav(onNavigateToReview = onNavigateToReview) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 22.dp, vertical = 24.dp)
            ) {
                DashboardHeader(onSync = onSync)

                Spacer(modifier = Modifier.height(22.dp))

                MonthlySpendCard(
                    monthlySpend = monthlySpend,
                    totalSpend = totalSpend,
                    transactionCount = monthlyTransactions.size
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SummaryTile(
                        label = "Tracked",
                        value = formatRupees(totalSpend),
                        modifier = Modifier.weight(1f)
                    )
                    SummaryTile(
                        label = "Top lane",
                        value = topCategory,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                PendingReviewCard(
                    reviewCount = reviewCount,
                    onNavigateToReview = onNavigateToReview
                )

                Spacer(modifier = Modifier.height(16.dp))

                SheetsIntegrationCard(
                    sheetsUiState = sheetsUiState,
                    onConnectGoogle = onConnectGoogle,
                    onExport = onExportToSheets,
                    onImport = onImportFromSheets
                )

                Spacer(modifier = Modifier.height(26.dp))

                CategoryBreakdown(categorySpend = categorySpend)

                Spacer(modifier = Modifier.height(26.dp))

                RecentActivity(transactions = transactions.take(5))

                Spacer(modifier = Modifier.height(18.dp))
            }

            if (isSyncing) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun DashboardHeader(onSync: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Namaste",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
            Text(
                text = "Your hisaab is ready",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
        }

        IconButton(
            onClick = onSync,
            modifier = Modifier
                .clip(CircleShape)
                .background(SurfaceVariant)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = "Sync")
        }
    }
}

@Composable
private fun MonthlySpendCard(
    monthlySpend: Double,
    totalSpend: Double,
    transactionCount: Int
) {
    val referenceBudget = 50000.0
    val progress = (monthlySpend / referenceBudget).coerceIn(0.02, 1.0).toFloat()

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardIvory),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            PrimaryIndigo.copy(alpha = 0.9f),
                            Color(0xFF202945)
                        )
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "This month",
                    color = Color.White.copy(alpha = 0.75f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = formatRupees(monthlySpend),
                    color = Color.White,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(18.dp))
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(100.dp)),
                    color = SaffronAccent,
                    trackColor = Color.White.copy(alpha = 0.18f)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "$transactionCount transactions this month - ${formatRupees(totalSpend)} tracked overall",
                    color = Color.White.copy(alpha = 0.78f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun SummaryTile(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(116.dp),
        colors = CardDefaults.cardColors(containerColor = CardIvory),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun PendingReviewCard(
    reviewCount: Int,
    onNavigateToReview: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onNavigateToReview),
        colors = CardDefaults.cardColors(containerColor = SurfaceVariant),
        shape = RoundedCornerShape(26.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SaffronAccent.copy(alpha = 0.22f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SaffronAccent)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Review stack",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$reviewCount cards ready to confirm or recategorize",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }

            Button(
                onClick = onNavigateToReview,
                colors = ButtonDefaults.buttonColors(containerColor = SaffronAccent),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Open", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SheetsIntegrationCard(
    sheetsUiState: SheetsUiState,
    onConnectGoogle: () -> Unit,
    onExport: () -> Unit,
    onImport: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardIvory),
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Google Sheets backup",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = "Export your ledger or restore from your Sheet.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(PrimaryIndigo.copy(alpha = 0.18f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.List, contentDescription = null, tint = PrimaryIndigo)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (sheetsUiState.isConnected) {
                ConnectedSheetSummary(sheetsUiState = sheetsUiState)

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(
                        onClick = onImport,
                        enabled = !sheetsUiState.isSyncing,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Import")
                    }

                    Button(
                        onClick = onExport,
                        enabled = !sheetsUiState.isSyncing,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
                    ) {
                        Text(if (sheetsUiState.isSyncing) "Syncing" else "Export")
                    }
                }
            } else {
                Text(
                    text = "No setup links. No scripts. MuneemJi will create a private Google Sheet in your account and sync directly with the Sheets API.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onConnectGoogle,
                    enabled = !sheetsUiState.isSyncing,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
                ) {
                    Text(if (sheetsUiState.isSyncing) "Connecting" else "Continue with Google")
                }
            }

            sheetsUiState.statusMessage?.let { message ->
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF4ADE80),
                    fontWeight = FontWeight.Bold
                )
            }

            sheetsUiState.errorMessage?.let { message ->
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ConnectedSheetSummary(sheetsUiState: SheetsUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceVariant)
            .padding(14.dp)
    ) {
        Text(
            text = "Connected to MuneemJi Expenses",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Spreadsheet ID: ${sheetsUiState.spreadsheetId.orEmpty()}",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (sheetsUiState.lastSyncedAt > 0L) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Last sync: ${formatFullDate(sheetsUiState.lastSyncedAt)}",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

@Composable
private fun CategoryBreakdown(categorySpend: List<CategorySpend>) {
    SectionTitle("Category breakdown")

    Spacer(modifier = Modifier.height(12.dp))

    if (categorySpend.isEmpty()) {
        EmptyDashboardCard("No categorized transactions yet.")
        return
    }

    val maxAmount = categorySpend.maxOf { it.amount }.coerceAtLeast(1.0)

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        categorySpend.take(5).forEach { category ->
            CategorySpendRow(
                category = category,
                progress = (category.amount / maxAmount).toFloat()
            )
        }
    }
}

@Composable
private fun CategorySpendRow(
    category: CategorySpend,
    progress: Float
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardIvory),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(category.name, fontWeight = FontWeight.Bold)
                Text(formatRupees(category.amount), color = TextSecondary)
            }
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(7.dp)
                    .clip(RoundedCornerShape(100.dp)),
                color = PrimaryIndigo,
                trackColor = SurfaceVariant
            )
        }
    }
}

@Composable
private fun RecentActivity(transactions: List<TransactionEntity>) {
    SectionTitle("Recent activity")

    Spacer(modifier = Modifier.height(12.dp))

    if (transactions.isEmpty()) {
        EmptyDashboardCard("Sync your SMS inbox to see recent activity here.")
        return
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        transactions.forEach { transaction ->
            RecentActivityItem(transaction = transaction)
        }
    }
}

@Composable
private fun RecentActivityItem(transaction: TransactionEntity) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardIvory),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(PrimaryIndigo.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = PrimaryIndigo)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.merchant ?: "Unknown merchant",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${transaction.category ?: "Uncategorized"} - ${formatDate(transaction.timestamp)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    maxLines = 1
                )
            }

            Text(
                text = formatRupees(transaction.amount ?: 0.0),
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
private fun EmptyDashboardCard(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardIvory),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(18.dp),
            color = TextSecondary
        )
    }
}

@Composable
fun DashboardBottomNav(onNavigateToReview: () -> Unit) {
    NavigationBar(
        containerColor = WarmBackground,
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryIndigo,
                selectedTextColor = PrimaryIndigo,
                indicatorColor = SurfaceVariant
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.CheckCircle, contentDescription = "Review") },
            label = { Text("Review") },
            selected = false,
            onClick = onNavigateToReview
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Hisab") },
            label = { Text("Hisab") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = {}
        )
    }
}

private fun TransactionEntity.isInCurrentMonth(): Boolean {
    val current = Calendar.getInstance()
    val transactionDate = Calendar.getInstance().apply {
        timeInMillis = timestamp
    }

    return current.get(Calendar.YEAR) == transactionDate.get(Calendar.YEAR) &&
        current.get(Calendar.MONTH) == transactionDate.get(Calendar.MONTH)
}

private fun formatRupees(amount: Double): String {
    return "₹${amount.roundToInt()}"
}

private fun formatDate(timestamp: Long): String {
    return SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(timestamp))
}

private fun formatFullDate(timestamp: Long): String {
    return SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault()).format(Date(timestamp))
}
