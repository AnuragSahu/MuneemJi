package com.muneemji.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// =========================
// Core Dark Palette
// =========================

val BackgroundDark = Color(0xFF0B0F1A)
val SurfaceDark = Color(0xFF151B2D)

val PrimaryIndigo = Color(0xFF7C8CFF)
val SuccessGreen = Color(0xFF4ADE80)
val ErrorRed = Color(0xFFFF6B6B)

val TextPrimary = Color(0xFFF5F7FA)
val TextSecondary = Color(0xFF9AA4BF)

// =========================
// Backward Compatibility Colors
// (Needed by existing UI screens)
// =========================

val SaffronAccent = Color(0xFFFFB347)
val WarmBackground = BackgroundDark
val CardIvory = SurfaceDark
val SurfaceVariant = Color(0xFF232B3E)
val TextDark = TextPrimary

// =========================
// Material Theme Colors
// =========================

private val DarkColors = darkColorScheme(
    primary = PrimaryIndigo,
    secondary = SuccessGreen,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = ErrorRed
)

// =========================
// Typography
// =========================

val AppTypography = Typography(

    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),

    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = TextPrimary
    ),

    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = TextSecondary
    )
)

// =========================
// App Theme
// =========================

@Composable
fun MuneemJiTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = AppTypography,
        content = content
    )
}