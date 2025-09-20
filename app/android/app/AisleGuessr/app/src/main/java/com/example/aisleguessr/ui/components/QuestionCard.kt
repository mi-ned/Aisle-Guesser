package com.example.aisleguessr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.aisleguessr.dimensions.Dimensions

@Composable
fun QuestionCard(
    levelNumber: Int,
    greetingMessage: String,
    queryMessage: String,
    hint: String
) {

    val shadowColour = if(isSystemInDarkTheme()) Color.Black else Color(0xFFC0C0C0)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Dimensions.QUESTION_CARD_SHADOW_PADDING, top = Dimensions.QUESTION_CARD_SHADOW_PADDING)
            .background(
                color = shadowColour,
                shape = RoundedCornerShape(Dimensions.QUESTION_CARD_CORNER_RADIUS)
            )
    ){
        Column(
            modifier = Modifier
                .offset(x = Dimensions.QUESTION_CARD_SHADOW_OFFSET, y = Dimensions.QUESTION_CARD_SHADOW_OFFSET)
                .fillMaxWidth()
                .background(
                    color = colorResource(com.example.aisleguessr.R.color.surface_colour),
                    shape = RoundedCornerShape(Dimensions.QUESTION_CARD_CORNER_RADIUS))
                .padding(Dimensions.COMPONENT_MARGIN),
            horizontalAlignment = Alignment.CenterHorizontally,
            ){

            //level number
            Text(
                text = "Level $levelNumber",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
            )

            //greetings
            Text(
                text = greetingMessage,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = Dimensions.COMPONENT_MARGIN)
            )

            //query
            Text(
                text = queryMessage,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            //hint
            Text(
                text = hint,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(top = Dimensions.COMPONENT_MARGIN)
                    .alpha(0f)
            )
        }
    }
}