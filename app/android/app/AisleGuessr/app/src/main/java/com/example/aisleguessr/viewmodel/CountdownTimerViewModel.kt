package com.example.aisleguessr.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


//move the logic into the GameViewModel
class CountdownTimerViewModel : ViewModel() {
    private val _timerDuration = MutableStateFlow(0f)
    val timerDuration = _timerDuration.asStateFlow()

    private val _maxDuration = MutableStateFlow(24f)
    val maxDuration = _maxDuration.asStateFlow()

    private val _remainingTime = MutableStateFlow(0L)
    val remainingTime = _remainingTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    private val _timeFraction = MutableStateFlow(1f)
    val timeFraction = _timeFraction.asStateFlow()

    private var timerJob: Job? = null

    init {
        setMaxDuration(_maxDuration.value)
    }

    val timerColour: StateFlow<Color> = _timeFraction
            .map { fraction ->
                when {
                    fraction > 0.75f -> Color(0xFF017EB3)
                    fraction > 0.5f -> Color(0xFF018701)
                    fraction > 0.25f -> Color(0xFFFF9901)
                    else -> Color(0xFFCC0122)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            Color.Unspecified
    )

    fun setDuration(duration: Float) {
        if (!_isRunning.value) {
            _timerDuration.update { duration }
            _remainingTime.update { duration.toLong() }
        }
    }

    fun setMaxDuration(duration: Float) {
        _maxDuration.value = duration
        _timerDuration.value = duration
        _remainingTime.value = duration.toLong()
        if (duration > 0) {
            _timeFraction.update { 1f }
        }
    }

    fun startTimer() {
        if (_isRunning.value) {
            timerJob?.cancel()
            _isRunning.value = false
        } else {
            if (_timerDuration.value > 0) {
                _isRunning.value = true
                timerJob = viewModelScope.launch {
                    val totalDurationMillis = _timerDuration.value * 1000L
                    val startTime = System.currentTimeMillis()

                    while(true){
                        val elapsedTime = System.currentTimeMillis() - startTime
                        val remainingTime = totalDurationMillis - elapsedTime

                        if(remainingTime <= 0){
                            _timeFraction.update { 0f }
                            _isRunning.value = false
                            break
                        }
                        _remainingTime.update { (remainingTime / 1000).toLong() }
                        _timeFraction.update { remainingTime / totalDurationMillis }
                        delay(16L)
                    }
                }
            }
        }
    }

    fun resetTimer(){
        timerJob?.cancel()
        _timerDuration.value = _maxDuration.value
        _remainingTime.value = _maxDuration.value.toLong()
        _isRunning.value = false
        _timeFraction.value = 1f
    }
}