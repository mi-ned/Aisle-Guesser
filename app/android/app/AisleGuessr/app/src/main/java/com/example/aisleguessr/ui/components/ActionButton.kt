package com.example.aisleguessr.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun ActionButton(@StringRes labelResId: Int,
                 onClick: () -> Unit,
                 modifier: Modifier = Modifier,
                 @StringRes contentDescriptionResId: Int? = null,
                 isEnabled: Boolean
){
    val labelText = stringResource(labelResId)
    val contentDescriptionText = if (contentDescriptionResId != null){
        stringResource(id = contentDescriptionResId)
    } else{
        labelText
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val targetButtonColour = if(isPressed) {
        colorResource(id = R.color.primary_pressed_colour)
    } else {
        colorResource(id = R.color.primary_colour)
    }

    val buttonColour by animateColorAsState(targetValue = targetButtonColour, label = "button colour")

    Button(
        onClick = onClick,
        modifier = modifier
            .semantics { contentDescription = contentDescriptionText }
            .height(Dimensions.DEFAULT_BUTTON_HEIGHT),
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColour,
            disabledContainerColor = colorResource(id = R.color.primary_disabled_colour),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(Dimensions.BUTTON_CORNER_RADIUS),
        enabled = isEnabled

    ){
        Text(
            text = stringResource(id = labelResId),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}