package com.example.aisleguessr.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun MainMenuButtons(
    @StringRes labelResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes contentDescriptionResId: Int? = null
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val targetTextColour = if(isPressed) {
        MaterialTheme.colorScheme.secondary
    } else {
        Color.White
    }
    val textColour by animateColorAsState(targetValue = targetTextColour, label = "button colour")

    val labelText = stringResource(id = labelResId)
    val contentDescriptionText = if (contentDescriptionResId != null){
        stringResource(id = contentDescriptionResId)
    } else{
        labelText
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .semantics { contentDescription = contentDescriptionText }
    ){
        Text(
            text = labelText,
            color = textColour,
            style = MaterialTheme.typography.labelLarge
        )
    }
}