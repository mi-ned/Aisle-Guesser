package com.example.aisleguessr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.aisleguessr.R
import com.example.aisleguessr.dimensions.Dimensions

@Composable
fun CountdownTimer(
    timeFraction: Float,
    timerColour: Color,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimensions.TIMER_HEIGHT)
            .clip(RoundedCornerShape(Dimensions.TIMER_CORNER_RADIUS))
            .background(colorResource(R.color.utility_colour))

    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(timeFraction)
                .clip(RoundedCornerShape(Dimensions.TIMER_CORNER_RADIUS))
                .background(timerColour)
        )
    }
}