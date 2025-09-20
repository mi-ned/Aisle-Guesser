package com.example.aisleguessr.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.aisleguessr.R
import com.example.aisleguessr.dimensions.Dimensions

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    @StringRes labelResId: Int,
    @StringRes contentDescriptionResId: Int? = null,
    value: String,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean,
    maxLengthOfTextField: Int,
    keyboardType: KeyboardOptions
    ){

    val labelText = stringResource(labelResId)
    val contentDescriptionText = contentDescriptionResId?.let {
        stringResource(id = it)
    } ?: labelText

    val isFocused = remember { mutableStateOf(false) }

    val unfocusedContainerColour = colorResource(id = R.color.utility_colour)
    val focusedContainerColour = colorResource(id = R.color.utility_colour_focused)
    val disabledContainerColour = colorResource(id = R.color.utility_colour_disabled)

    TextField(
        value = value,
        onValueChange = { newText ->
            if(newText.length <= maxLengthOfTextField){
                onValueChange(newText.replace("\n", ""))
            } },
        modifier = modifier
            .semantics { contentDescription = contentDescriptionText }
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused
            },
        enabled = isEnabled,
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            val labelStyle = if (isFocused.value) {
                MaterialTheme.typography.bodySmall
            } else {
                MaterialTheme.typography.bodyMedium
            }
            Text(
                text = labelText,
                style = labelStyle
            )},
        leadingIcon = leadingIcon,
        trailingIcon = {
            if(value.isNotEmpty()){
                IconButton(
                    onClick = { onValueChange("")}
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.clear_search_text_description)
                    )
                }
            }
        },
        maxLines = 1,
        keyboardOptions = keyboardType,
        shape = RoundedCornerShape(Dimensions.NO_CORNER_RADIUS),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = unfocusedContainerColour,
            focusedContainerColor = focusedContainerColour,
            disabledContainerColor = disabledContainerColour,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            disabledTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            disabledLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.secondary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
            disabledTrailingIconColor = MaterialTheme.colorScheme.primary
        )
    )
}