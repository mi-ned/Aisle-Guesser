package com.example.aisleguessr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisleguessr.repository.HighScoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val highScoreRepository: HighScoreRepository
): ViewModel() {

    //scores
    private val _currentScore = MutableStateFlow(0)
    val currentScore: StateFlow<Int> = _currentScore

    private val _highScore = MutableStateFlow(0)
    val highScore: StateFlow<Int> = _highScore

    init {
        viewModelScope.launch {
            highScoreRepository.highScoreFlow.collect { score ->
                _highScore.value = score
            }
        }
    }

    fun incrementCurrentScore(points: Int){
        _currentScore.value += points
    }

    fun updateHighScore(newScore: Int){
        if(newScore > _highScore.value){
            viewModelScope.launch {
                highScoreRepository.saveHighScore(newScore)
            }
        }
    }

    fun submitAnswer(answer: String){
        //TODO
    }

    fun onGameOver() {
        updateHighScore(_currentScore.value)
    }
}