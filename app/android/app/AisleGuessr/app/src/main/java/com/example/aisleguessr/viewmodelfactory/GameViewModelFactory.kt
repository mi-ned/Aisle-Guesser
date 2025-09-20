package com.example.aisleguessr.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aisleguessr.repository.HighScoreRepository
import com.example.aisleguessr.viewmodel.GameViewModel

class GameViewModelFactory(private val highScoreRepository: HighScoreRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(highScoreRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}