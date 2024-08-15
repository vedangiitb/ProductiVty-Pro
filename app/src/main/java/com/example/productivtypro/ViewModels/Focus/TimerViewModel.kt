package com.example.productivtypro.ViewModels.Focus

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimerViewModel:ViewModel() {
    private val _totalTime = MutableStateFlow(1500L)
    val totalTime = _totalTime.asStateFlow()

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private var timerJob: Job? = null

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timer.value++
                if (_timer.value == _totalTime.value){
                    _timer.value = 0
                    break
                }
            }
        }
    }

    fun setTotalTime(minutes:Int,seconds:Int){
        _totalTime.value = (minutes*60 + seconds).toLong()
    }

    fun stopTimer() {
        _timer.value = 0
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}