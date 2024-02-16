package com.example.resources.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView


private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    tertiary = accentColor,
)

@Composable
fun WarehouseTheme(
    content: @Composable () -> Unit,
) {
    val colors = LightColorScheme
    val view = LocalView.current

    val window = (view.context as Activity).window
    window.statusBarColor = LightColorScheme.primary.toArgb()

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}