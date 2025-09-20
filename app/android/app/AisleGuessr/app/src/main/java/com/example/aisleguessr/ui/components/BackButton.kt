package com.example.aisleguessr.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.aisleguessr.R
import com.example.aisleguessr.dimensions.Dimensions

@Composable
fun BackButton(
    @StringRes labelResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes contentDescriptionResId: Int? = null,
){

    val labelText = stringResource(labelResId)
    val contentDescriptionText = if (contentDescriptionResId != null){
        stringResource(id = contentDescriptionResId)
    } else{
        labelText
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val targetTextColour = if(isPressed) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onBackground
    }
    val textColour by animateColorAsState(targetValue = targetTextColour, label = "button text colour")

    val targetButtonColour = if(isPressed) {
        colorResource(id = R.color.secondary_pressed_colour)
    } else {
        Color.Transparent
    }
    val buttonColour by animateColorAsState(targetValue = targetButtonColour, label = "button colour")

    TextButton(
        onClick = onClick,
        modifier = modifier
            .semantics { contentDescription = contentDescriptionText },
        interactionSource = interactionSource,
        colors = ButtonDefaults.textButtonColors(
            containerColor = buttonColour,
            contentColor = textColour
        ),
        shape = RoundedCornerShape(Dimensions.NO_CORNER_RADIUS),
    ){
        Text(
            text = stringResource(id = labelResId),
            style = MaterialTheme.typography.labelMedium
        )
    }
}