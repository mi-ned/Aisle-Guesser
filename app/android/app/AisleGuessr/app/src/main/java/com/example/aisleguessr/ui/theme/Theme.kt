package com.example.aisleguessr.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.aisleguessr.R

@Composable
fun AisleGuessrTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val lightColourPalette = lightColorScheme(
        primary = colorResource(R.color.primary_colour),
        onPrimary = colorResource(R.color.text_colour),
        secondary = colorResource(R.color.secondary_colour),
        onSecondary = colorResource(R.color.text_colour),
        tertiary = colorResource(R.color.tertiary_colour),
        onTertiary = colorResource(R.color.text_colour),
        background = colorResource(R.color.background_colour),
        onBackground = colorResource(R.color.text_colour),
        surface = colorResource(R.color.surface_colour),
        onSurface = colorResource(R.color.text_colour),
        error = colorResource(R.color.status_error_colour),
        onError = Color.White
    )

    val darkColourPalette = darkColorScheme(
        primary = colorResource(R.color.primary_colour),
        onPrimary = colorResource(R.color.text_colour),
        secondary = colorResource(R.color.secondary_colour),
        onSecondary = colorResource(R.color.text_colour),
        tertiary = colorResource(R.color.tertiary_colour),
        onTertiary = colorResource(R.color.text_colour),
        background = colorResource(R.color.background_colour),
        onBackground = colorResource(R.color.text_colour),
        surface = colorResource(R.color.surface_colour),
        onSurface = colorResource(R.color.text_colour),
        error = colorResource(R.color.status_error_colour),
        onError = Color.White
    )

    val colourScheme = if (darkTheme) darkColourPalette else lightColourPalette

    MaterialTheme(
        colorScheme = colourScheme,
        typography = Typography,
        content = content
    )
}