package com.molyavin.mvvm.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = Colors(
    primary = Color(0xFF1C1B1F),
    secondary = Color.White,
    background = Color.White,
    onPrimary = Color(0xFF1C1B1F),
    surface = Pink80,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Pink80,
    primaryVariant = Color(0xFF1C1B1F),
    secondaryVariant = Color.White,
    error = Color.Red,
    onError = Color.Red,
    isLight = true,
)

private val DarkColorScheme = Colors(
    primary = Color.White,
    secondary = Color(0xFF1C1B1F),
    background = Color(0xFF1C1B1F),
    onPrimary = Color.White,
    surface = Pink80,
    onSecondary = Color(0xFF1C1B1F),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Pink80,
    primaryVariant = Color.White,
    secondaryVariant = Color(0xFF1C1B1F),
    error = Color.Red,
    onError = Color.Red,
    isLight = false,
)

@Composable
fun MVVMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}