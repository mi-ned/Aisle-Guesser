package com.example.aisleguessr.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aisleguessr.R
import com.example.aisleguessr.dimensions.Dimensions
import com.example.aisleguessr.repository.HighScoreRepository
import com.example.aisleguessr.ui.components.ActionButton
import com.example.aisleguessr.ui.components.AppTextField
import com.example.aisleguessr.ui.components.BackButton
import com.example.aisleguessr.ui.components.CountdownTimer
import com.example.aisleguessr.ui.components.LifelineButton
import com.example.aisleguessr.ui.components.LivesIndicator
import com.example.aisleguessr.ui.components.QuestionCard
import com.example.aisleguessr.ui.components.ScoreDisplay
import com.example.aisleguessr.ui.theme.AisleGuessrTheme
import com.example.aisleguessr.viewmodel.GameViewModel
import com.example.aisleguessr.viewmodelfactory.GameViewModelFactory

@Composable
fun GameScreen(modifier: Modifier = Modifier, onBackClicked: () -> Unit, viewModel: GameViewModel = viewModel(factory = GameViewModelFactory(HighScoreRepository(LocalContext.current)))){

    val highScoreHeader = stringResource(R.string.high_score)
    val currentScoreHeader = stringResource(R.string.current_score)

    val currentScoreValue by viewModel.currentScore.collectAsStateWithLifecycle()
    val highScoreValue by viewModel.highScore.collectAsStateWithLifecycle()

    //Page
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding(),
    ) {
        //header/exit button
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.play),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        }

        //current/high scores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimensions.SCREEN_MARGIN, start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ScoreDisplay(
                content = currentScoreHeader,
                value = currentScoreValue,
            )
            ScoreDisplay(
                content = highScoreHeader,
                value = highScoreValue,
            )
        }

        //timer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, top = Dimensions.SCREEN_MARGIN),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            CountdownTimer(
                timeFraction = TODO(),
                timerColour = TODO()
            )
        }

        //lives
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimensions.SCREEN_MARGIN, start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN),
            contentAlignment = Alignment.Center
        ){
            LivesIndicator(
                totalLives = 3,
                currentLives = 2
            )
        }

        //question card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimensions.SCREEN_MARGIN, start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN),
            contentAlignment = Alignment.Center
        ){
            //The following parameters that are passed are all dummy values
            QuestionCard(
                levelNumber = 1,
                greetingMessage = "Oi!",
                queryMessage = "Where can I find some roast beef?\n",
                hint = "Frozen"
            )
        }

        //lifeline buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, top = Dimensions.SCREEN_MARGIN),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.COMPONENT_MARGIN),
            verticalAlignment = Alignment.CenterVertically
        ){
            LifelineButton(
                modifier = Modifier.weight(1f),
                labelResId = R.string.unavailable
            )

            LifelineButton(
                modifier = Modifier.weight(1f),
                labelResId = R.string.upstairs
            )

            LifelineButton(
                modifier = Modifier.weight(1f),
                labelResId = R.string.hint_options
            )
        }

        var numberText by remember { mutableStateOf("")}

        //answer box
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimensions.SCREEN_MARGIN, start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN)
        ){
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                labelResId = R.string.enter_answer_here,
                contentDescriptionResId = R.string.enter_answer_here,
                value = numberText,
                onValueChange = {numberText = it},
                isEnabled = true,
                maxLengthOfTextField = 2,
                keyboardType = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "Wrong aisle! You berk!",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .align(Alignment.End)
                    .alpha(1f),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        //submit button
        ActionButton(
            labelResId = R.string.play,
            onClick = { },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, bottom = Dimensions.SCREEN_MARGIN),
            contentDescriptionResId = R.string.play,
            isEnabled = false
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun GameScreenPreviewLight(){
    AisleGuessrTheme {
        GameScreen(onBackClicked = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GameScreenPreviewDark(){
    AisleGuessrTheme {
        GameScreen(onBackClicked = {})
    }
}