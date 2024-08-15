package com.example.productivtypro.ViewModels.Task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivtypro.data.Tasks.TaskSummary
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.data.Tasks.TasksRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TaskDisplayViewModel(private val tasksRepo: TasksRepo):ViewModel() {
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val _datetime = mutableStateOf("")
    val datetime: State<String> = _datetime

    val taskDispUiState: StateFlow<TasksDispUiState> =
        tasksRepo.getAllTasksStream().map { TasksDispUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = TasksDispUiState()
        )

    val taskDoneFilterUiState: StateFlow<TaskDoneUiState> =
        tasksRepo.getOverNotOver().map { TaskDoneUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = TaskDoneUiState()
        )
}

data class TasksDispUiState(val tasksList: List<TasksData> = listOf())

data class TaskDoneUiState(val tasksList: List<TaskSummary> = listOf())