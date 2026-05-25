@file:OptIn(ExperimentalMaterial3Api::class)
package com.muneemji.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import com.muneemji.app.db.TransactionEntity
import com.muneemji.app.ui.theme.PrimaryIndigo
import com.muneemji.app.ui.theme.SaffronAccent
import com.muneemji.app.ui.theme.SurfaceVariant
import com.muneemji.app.ui.theme.TextSecondary
import kotlin.math.roundToInt

// ---------------------- DATA ----------------------

data class MockTransaction(
    val id: Int,
    val merchant: String,
    val amount: String,
    val category: String,
    val date: String,
    val time: String,
    val note: String
)

// ---------------------- SCREEN ----------------------

@Composable
fun ReviewStackScreen(
    transactions: List<TransactionEntity>,
    onBack: () -> Unit,
    onCategorySelected: (transactionId: Int, category: String) -> Unit
) {
    var items by remember {
        mutableStateOf(
            transactions.map {
                MockTransaction(
                    id = it.id,
                    merchant = it.merchant ?: "Unknown",
                    amount = "₹${(it.amount ?: 0.0).roundToInt()}",
                    category = it.category ?: "Uncategorized",
                    date = "",
                    time = "",
                    note = it.body
                )
            }
        )
    }

    var showSheet by remember { mutableStateOf(false) }
    var categoryTransaction by remember { mutableStateOf<MockTransaction?>(null) }

    fun removeTopItem() {
        items = items.drop(1)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Review Transactions") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {

            val visibleItems = items.take(4)

            visibleItems.asReversed().forEachIndexed { drawIndex, tx ->
                val stackIndex = visibleItems.lastIndex - drawIndex
                val isTopCard = stackIndex == 0

                key(tx.id) {
                    SwipeableCard(
                        transaction = tx,
                        index = stackIndex,
                        isTop = isTopCard,

                        onSwipedLeft = {
                            categoryTransaction = tx
                            showSheet = true
                            removeTopItem()
                        },

                        onSwipedRight = {
                            removeTopItem()
                        }
                    )
                }
            }

            if (items.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("All caught up!", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Your review stack is clear.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
            }

            categoryTransaction?.let { transaction ->
                if (showSheet) {
                    CategoryPickerCard(
                        transaction = transaction,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(8.dp)
                            .fillMaxWidth()
                            .zIndex(20f),
                        onDismiss = {
                            showSheet = false
                            categoryTransaction = null
                        },
                        onCategorySelected = { category ->
                            onCategorySelected(transaction.id, category)
                            showSheet = false
                            categoryTransaction = null
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryPickerCard(
    transaction: MockTransaction,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf(
        "Food",
        "Travel",
        "Shopping",
        "Bills",
        "Groceries",
        "Health",
        "Entertainment",
        "Transfers",
        "Other"
    )

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Choose category",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${transaction.merchant} - ${transaction.amount}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }

                TextButton(onClick = onDismiss) {
                    Text("Skip")
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            categories.chunked(3).forEach { rowCategories ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowCategories.forEach { category ->
                        CategoryOption(
                            category = category,
                            selected = transaction.category == category,
                            modifier = Modifier.weight(1f),
                            onClick = { onCategorySelected(category) }
                        )
                    }

                    repeat(3 - rowCategories.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun CategoryOption(
    category: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val containerColor = if (selected) PrimaryIndigo.copy(alpha = 0.22f) else SurfaceVariant
    val borderColor = if (selected) PrimaryIndigo else Color.Transparent

    Column(
        modifier = modifier
            .height(86.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(containerColor)
            .border(1.dp, borderColor, RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (selected) SaffronAccent else PrimaryIndigo.copy(alpha = 0.24f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category.take(1),
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

// ---------------------- SWIPE CARD ----------------------

@Composable
fun SwipeableCard(
    transaction: MockTransaction,
    index: Int,
    isTop: Boolean,
    onSwipedLeft: () -> Unit,
    onSwipedRight: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var rotation by remember { mutableStateOf(0f) }

    var isRemoving by remember { mutableStateOf(false) }

    val scale = 1f - (index * 0.05f)
    val yOffset = (index * 20).dp

    val animatedScale = scale
    val animatedYOffset = yOffset
    val animatedYOffsetPx = with(LocalDensity.current) { animatedYOffset.roundToPx() }

    LaunchedEffect(transaction.id, isTop) {
        if (isTop) {
            offsetX = 0f
            offsetY = 0f
            rotation = 0f
            isRemoving = false
        }
    }

    Card(
        modifier = Modifier
            .zIndex((10 - index).toFloat())
            .fillMaxWidth()
            .aspectRatio(0.72f)
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt() + animatedYOffsetPx) }
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
                rotationZ = rotation
                alpha = 1f
            }
            .then(
                if (isTop && !isRemoving) {
                    Modifier.pointerInput(transaction.id, isTop) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                                rotation = offsetX / 25f
                            },

                            onDragEnd = {
                                val threshold = screenWidthPx * 0.3f

                                when {
                                    offsetX > threshold -> {
                                        isRemoving = true
                                        rotation = 12f
                                        onSwipedRight()
                                    }

                                    offsetX < -threshold -> {
                                        isRemoving = true
                                        rotation = -12f
                                        onSwipedLeft()
                                    }

                                    else -> {
                                        offsetX = 0f
                                        offsetY = 0f
                                        rotation = 0f
                                        isRemoving = false
                                    }
                                }
                            }
                        )
                    }
                } else Modifier
            ),
        shape = RoundedCornerShape(24.dp)
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Text(transaction.amount, fontSize = 40.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(12.dp))

            Text(transaction.merchant, fontSize = 22.sp)

            Spacer(Modifier.weight(1f))

            Text("Category: ${transaction.category}")
        }
    }
}
