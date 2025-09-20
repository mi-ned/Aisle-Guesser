package com.example.aisleguessr.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisleguessr.R
import com.example.aisleguessr.data.ButtonData
import com.example.aisleguessr.navigation.NavigationRoutes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainMenuViewModel : ViewModel() {

    private val _navigateTo = Channel<String>()
    val navigateTo = _navigateTo.receiveAsFlow()

    val buttonList: List<ButtonData> = listOf(
        ButtonData(R.string.disclaimer, false),
        ButtonData(R.string.play, false),
        ButtonData(R.string.glossary, false),
        ButtonData(R.string.settings, false),
        ButtonData(R.string.information, false),
    )

    fun onButtonClicked(@StringRes labelResId: Int){
        viewModelScope.launch{
            when(labelResId) {
                R.string.disclaimer -> _navigateTo.send(NavigationRoutes.DISCLAIMER)
                R.string.play -> _navigateTo.send(NavigationRoutes.PLAY)
                R.string.glossary -> _navigateTo.send(NavigationRoutes.GLOSSARY)
            }
        }
    }
}