package com.example.aisleguessr.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.aisleguessr.R

@Composable
fun HeartIcon(isFull: Boolean, modifier: Modifier = Modifier){
    val tintColor = if(isFull) Color.Unspecified else Color.Unspecified
    Icon(
        painter = painterResource(
            id = if(isFull) R.drawable.heart else if(isSystemInDarkTheme()) R.drawable.heartless_dark else R.drawable.heartless_light),
        modifier = modifier,
        contentDescription = if(isFull) "full_heart" else "empty_heart",
        tint = tintColor
        )
}