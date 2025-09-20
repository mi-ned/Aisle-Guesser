package com.example.aisleguessr.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.aisleguessr.R
import com.example.aisleguessr.dimensions.Dimensions
import com.example.aisleguessr.ui.components.BackButton
import com.example.aisleguessr.ui.theme.AisleGuessrTheme

@Composable
fun DisclaimerScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
){
    //Page
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //Button & Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, top = Dimensions.SCREEN_MARGIN),
            contentAlignment = Alignment.Center
        ) {
            BackButton(
                labelResId = R.string.back_arrow,
                onClick = onBackClicked,
                modifier = Modifier.align(Alignment.CenterStart),
                contentDescriptionResId = R.string.back_arrow
            )
            Text(
                text = stringResource(id = R.string.disclaimer),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        //Message
        Text(
            text = getDisclaimerText(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, top = Dimensions.SCREEN_MARGIN, bottom = Dimensions.SCREEN_MARGIN)
                .verticalScroll(rememberScrollState())
            )

        //Last Bullet Point
        val disclaimerPoints = stringArrayResource(id = R.array.disclaimer_points)
        val lastPoint = disclaimerPoints.last()

        Text(
            text = "\u2022 $lastPoint",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, bottom = Dimensions.SCREEN_MARGIN)
        )
    }
}

@Composable
private fun getDisclaimerText(): AnnotatedString {
    val disclaimerPoints = stringArrayResource(id = R.array.disclaimer_points)
    val bodyMediumStyle = MaterialTheme.typography.bodyMedium

    val pointsForScroll = disclaimerPoints.dropLast(1)

    return buildAnnotatedString {
        pointsForScroll.forEachIndexed { index, point ->
            val isFirstOrLast = index == 0

            if (isFirstOrLast) {
                withStyle(style = bodyMediumStyle.toSpanStyle().copy(fontWeight = FontWeight.Bold)) {
                    append("\u2022 ")
                    append(point)
                }
            } else {
                append("\u2022 ")
                append(point)
            }
            if (index < disclaimerPoints.lastIndex) {
                append("\n\n")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun DisclaimerScreenPreviewLight(){
    AisleGuessrTheme {
        DisclaimerScreen(onBackClicked = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DisclaimerScreenPreviewDark(){
    AisleGuessrTheme {
        DisclaimerScreen(onBackClicked = {})
    }
}