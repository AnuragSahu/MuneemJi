package com.muneemji.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muneemji.app.ui.theme.PrimaryIndigo
import com.muneemji.app.ui.theme.SaffronAccent
import com.muneemji.app.ui.theme.SurfaceVariant
import com.muneemji.app.ui.theme.TextSecondary
import com.muneemji.app.ui.theme.WarmBackground

private data class OnboardingPage(
    val eyebrow: String,
    val title: String,
    val body: String,
    val accent: Color
)

@Composable
fun OnboardingScreen(
    onFinished: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            eyebrow = "Smart SMS tracking",
            title = "Your expenses, collected quietly",
            body = "MuneemJi reads transaction SMS on your device and turns them into a clean spending timeline.",
            accent = PrimaryIndigo
        ),
        OnboardingPage(
            eyebrow = "Review with a swipe",
            title = "Confirm what is right, fix what is not",
            body = "Swipe right when a transaction looks correct. Swipe left to choose a better category in seconds.",
            accent = SaffronAccent
        ),
        OnboardingPage(
            eyebrow = "Sheets-ready",
            title = "Keep your hisaab portable",
            body = "Connect a Google Sheet to export your ledger or restore it when you need a backup.",
            accent = Color(0xFF4ADE80)
        )
    )

    var pageIndex by remember { mutableStateOf(0) }
    val page = pages[pageIndex]
    val isLastPage = pageIndex == pages.lastIndex

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmBackground)
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .size(220.dp)
                .align(Alignment.TopEnd)
                .offset(x = 80.dp, y = (-54).dp)
                .clip(CircleShape)
                .background(page.accent.copy(alpha = 0.18f))
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (!isLastPage) {
                    OutlinedButton(onClick = onFinished) {
                        Text("Skip")
                    }
                }
            }

            Column(horizontalAlignment = Alignment.Start) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(page.accent, page.accent.copy(alpha = 0.45f))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "₹",
                        color = Color.White,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = page.eyebrow.uppercase(),
                    color = page.accent,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = page.title,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 38.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = page.body,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextSecondary,
                    lineHeight = 24.sp
                )
            }

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    pages.forEachIndexed { index, _ ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .height(8.dp)
                                .width(if (index == pageIndex) 28.dp else 8.dp)
                                .clip(CircleShape)
                                .background(if (index == pageIndex) page.accent else SurfaceVariant)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (isLastPage) {
                            onFinished()
                        } else {
                            pageIndex += 1
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = page.accent),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text(
                        text = if (isLastPage) "Start tracking" else "Continue",
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                if (pageIndex > 0) {
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedButton(
                        onClick = { pageIndex -= 1 },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text("Back", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
