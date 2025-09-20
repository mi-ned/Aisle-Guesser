package com.example.aisleguessr.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.aisleguessr.R
import com.example.aisleguessr.dimensions.Dimensions
import com.example.aisleguessr.ui.components.MainMenuButtons
import com.example.aisleguessr.ui.theme.AisleGuessrTheme
import com.example.aisleguessr.viewmodel.MainMenuViewModel

@Composable
fun MainMenuScreen(
    modifier: Modifier = Modifier,
    viewModel: MainMenuViewModel,
) {
    val buttonList = viewModel.buttonList

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .systemBarsPadding(),
    ){

        //Image/Title
        Spacer(modifier = Modifier.weight(0.5f))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_white),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.MAIN_MENU_IMAGE_MARGIN)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color.White,
                    style = MaterialTheme.typography.displayLarge
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            //Buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Dimensions.SCREEN_MARGIN, end = Dimensions.SCREEN_MARGIN, bottom = Dimensions.SCREEN_MARGIN),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.COMPONENT_MARGIN)
            ) {
                buttonList.forEach { buttonData ->
                    MainMenuButtons(
                        labelResId = buttonData.labelResId,
                        onClick = {
                                viewModel.onButtonClicked(buttonData.labelResId)
                        },
                        contentDescriptionResId = when (buttonData.labelResId){
                            R.string.disclaimer -> R.string.disclaimer
                            R.string.play -> R.string.play //temp
                            R.string.glossary -> R.string.glossary
                            //settings
                            //info
                            else -> null
                        },
                    )
                }
            }
        }
    }

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MainMenuScreenPreviewLight(){
    AisleGuessrTheme {
        MainMenuScreen(
            viewModel = MainMenuViewModel()
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainMenuScreenPreviewDark(){
    AisleGuessrTheme {
        MainMenuScreen(
            viewModel = MainMenuViewModel()
        )
    }
}