package com.example.productivtypro.ViewModels.Habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivtypro.data.Habit.HabitData
import com.example.productivtypro.data.Habit.HabitRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HabitDisplayViewModel(private val habitRepo: HabitRepo): ViewModel()  {
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val habitDispUiState: StateFlow<HabitDispUiState> =
        habitRepo.getAllHabits().map { HabitDispUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(HabitDisplayViewModel.TIMEOUT_MILLIS),
            initialValue = HabitDispUiState()
        )
}


data class HabitDispUiState(val habitsList: List<HabitData> = listOf())