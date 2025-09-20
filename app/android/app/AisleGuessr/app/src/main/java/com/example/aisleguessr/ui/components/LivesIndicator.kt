package com.example.aisleguessr.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aisleguessr.dimensions.Dimensions

@Composable
fun LivesIndicator(modifier: Modifier = Modifier, totalLives: Int, currentLives: Int) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(Dimensions.COMPONENT_MARGIN),
        ) {
            repeat(totalLives){ index ->
                HeartIcon(
                    isFull = index < currentLives,
                    modifier = Modifier.size(Dimensions.LIVES_ICON_HEIGHT)
                )
            }
        }
}