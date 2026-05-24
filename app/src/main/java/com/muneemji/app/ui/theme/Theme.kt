package com.muneemji.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val PrimaryIndigo = Color(0xFF1A237E)
val SaffronAccent = Color(0xFFFF8F00)
val SuccessGreen = Color(0xFF2E7D32)
val WarmBackground = Color(0xFFFDFBF7)
val TextDark = Color(0xFF121212)
val CardIvory = Color(0xFFFAF9F5)
val SurfaceVariant = Color(0xFFEBE8E0)
val ErrorRed = Color(0xFFD32F2F)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryIndigo,
    secondary = SaffronAccent,
    tertiary = SuccessGreen,
    background = WarmBackground,
    surface = CardIvory,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextDark,
    onSurface = TextDark,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = Color(0xFF5D5D5D),
    error = ErrorRed
)

// Inter / modern sans-serif look using default for now, can be configured to load fonts
val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = Color(0xFF5D5D5D)
    )
)

@Composable
fun MuneemJiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Forcing light theme for now to match the warm aesthetic requested
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme, // Use light scheme primarily to enforce the warm paper aesthetic
        typography = AppTypography,
        content = content
    )
}
