package com.muneemji.app.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muneemji.app.ui.theme.*
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

data class MockTransaction(
    val id: String,
    val merchant: String,
    val amount: String,
    val category: String,
    val date: String,
    val time: String,
    val note: String
)

val mockTransactions = listOf(
    MockTransaction("1", "Swiggy", "₹450", "Food", "3rd time this week", "9:15 PM", "Food Delivery"),
    MockTransaction("2", "Uber", "₹220", "Travel", "Yesterday", "10:30 AM", "Office Commute"),
    MockTransaction("3", "Starbucks", "₹350", "Cafe", "Yesterday", "4:00 PM", "Coffee"),
    MockTransaction("4", "Amazon", "₹1,299", "Shopping", "Mon, 12 Oct", "2:00 PM", "Electronics"),
    MockTransaction("5", "Blinkit", "₹580", "Groceries", "Sun, 11 Oct", "8:15 AM", "Groceries"),
    MockTransaction("6", "Zepto", "₹320", "Groceries", "Sat, 10 Oct", "7:45 PM", "Snacks"),
    MockTransaction("7", "Zomato", "₹650", "Food", "Fri, 9 Oct", "9:00 PM", "Dinner"),
    MockTransaction("8", "Airtel", "₹799", "Bills", "Thu, 8 Oct", "10:00 AM", "Broadband")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewStackScreen(
    onBack: () -> Unit
) {
    var items by remember { mutableStateOf(mockTransactions) }
    var showCategorySheet by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = WarmBackground,
        topBar = {
            TopAppBar(
                title = { Text("Review Transactions", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = WarmBackground
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Progress Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Progress", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("${mockTransactions.size - items.size + 1} / ${mockTransactions.size}", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = (mockTransactions.size - items.size + 1).toFloat() / mockTransactions.size.coerceAtLeast(1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = PrimaryIndigo,
                    trackColor = SurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Stack Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (items.isEmpty()) {
                    Text("All caught up!", style = MaterialTheme.typography.headlineMedium)
                } else {
                    // Draw in reverse so the first item is on top
                    items.reversed().forEachIndexed { index, transaction ->
                        val reversedIndex = items.size - 1 - index
                        if (reversedIndex < 4) {
                            SwipeableCard(
                                transaction = transaction,
                                indexInStack = reversedIndex,
                                onSwipedRight = {
                                    items = items.drop(1)
                                },
                                onSwipedLeft = {
                                    showCategorySheet = true
                                    items = items.drop(1)
                                }
                            )
                        }
                    }
                }
            }

            // Hint Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Swipe left\nto change", textAlign = TextAlign.Start, color = ErrorRed, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.Refresh, contentDescription = "Swipe", modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Swipe right\nif correct", textAlign = TextAlign.End, color = SuccessGreen, fontWeight = FontWeight.Bold)
            }
        }

        if (showCategorySheet) {
            CategoryGridSheet(
                onDismiss = { showCategorySheet = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryGridSheet(
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable { onDismiss() },
        contentAlignment = Alignment.BottomCenter
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardIvory
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "Select Category",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                val categories = listOf(
                    "Food",
                    "Travel",
                    "Shopping",
                    "Bills",
                    "Groceries",
                    "Entertainment",
                    "Health",
                    "Other"
                )

                categories.chunked(2).forEach { rowItems ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        rowItems.forEach { category ->

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(SurfaceVariant)
                                    .clickable {
                                        onDismiss()
                                    }
                                    .padding(vertical = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = category,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo
                                )
                            }
                        }

                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun SwipeableCard(
    transaction: MockTransaction,
    indexInStack: Int,
    onSwipedRight: () -> Unit,
    onSwipedLeft: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }
    val swipeThreshold = screenWidthPx * 0.3f

    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    // Calculate background card properties
    val targetScale = 1f - (indexInStack * 0.05f)
    val targetOffsetY = (indexInStack * 20).dp
    val targetAlpha = 1f - (indexInStack * 0.15f)

    val animatedScale by animateFloatAsState(targetValue = targetScale, animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f))
    val animatedOffsetY by animateDpAsState(targetValue = targetOffsetY, animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f))
    val animatedAlpha by animateFloatAsState(targetValue = targetAlpha, animationSpec = tween(300))

    val isTopCard = indexInStack == 0

    val modifier = if (isTopCard) {
        Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt() + animatedOffsetY.value.roundToInt()) }
            .graphicsLayer {
                rotationZ = rotation.value
                scaleX = animatedScale
                scaleY = animatedScale
                alpha = animatedAlpha
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        scope.launch {
                            if (offsetX.value > swipeThreshold) {
                                // Swipe Right
                                launch { offsetX.animateTo(screenWidthPx * 1.5f, tween(300)) }
                                launch { rotation.animateTo(15f, tween(300)) }
                                onSwipedRight()
                            } else if (offsetX.value < -swipeThreshold) {
                                // Swipe Left
                                launch { offsetX.animateTo(-screenWidthPx * 1.5f, tween(300)) }
                                launch { rotation.animateTo(-15f, tween(300)) }
                                onSwipedLeft()
                            } else {
                                // Snap back
                                launch { offsetX.animateTo(0f, spring(dampingRatio = 0.6f, stiffness = 800f)) }
                                launch { offsetY.animateTo(0f, spring(dampingRatio = 0.6f, stiffness = 800f)) }
                                launch { rotation.animateTo(0f, spring(dampingRatio = 0.6f, stiffness = 800f)) }
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                            offsetY.snapTo(offsetY.value + dragAmount.y)
                            rotation.snapTo(offsetX.value / 20f)
                        }
                    }
                )
            }
    } else {
        Modifier
            .offset { IntOffset(0, animatedOffsetY.value.roundToInt()) }
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
                alpha = animatedAlpha
            }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.75f),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = CardIvory),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isTopCard) 8.dp else 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            
            // Swipe Indicators
            if (isTopCard && abs(offsetX.value) > 50f) {
                val isRight = offsetX.value > 0
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = if (isRight) Alignment.TopStart else Alignment.TopEnd
                ) {
                    Box(
                        modifier = Modifier
                            .border(
                                4.dp,
                                if (isRight) SuccessGreen else ErrorRed,
                                RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .graphicsLayer { rotationZ = if (isRight) -15f else 15f }
                    ) {
                        Text(
                            text = if (isRight) "CORRECT" else "CHANGE",
                            color = if (isRight) SuccessGreen else ErrorRed,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 32.sp,
                            letterSpacing = 2.sp
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Tag
                Box(
                    modifier = Modifier
                        .background(SurfaceVariant, RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("AI thinks: ${transaction.category}", fontWeight = FontWeight.SemiBold, color = PrimaryIndigo)
                }

                Spacer(modifier = Modifier.weight(1f))

                // Center Amount & Merchant
                Text(
                    text = transaction.amount,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = transaction.merchant,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Category Chip
                Box(
                    modifier = Modifier
                        .background(SaffronAccent.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(transaction.category, color = SaffronAccent, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.weight(1f))

                // Bottom Details
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text("Spent at ${transaction.time}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(transaction.date, fontWeight = FontWeight.Medium)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                }
            }
        }
    }
}
