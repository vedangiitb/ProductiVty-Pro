package com.example.productivtypro.ViewModels.Focus

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivtypro.data.Focus.FocusData
import com.example.productivtypro.data.Focus.FocusRepo
import kotlinx.coroutines.launch

class FocusViewModel(private val focusRepo: FocusRepo): ViewModel() {
    private val _datetime = mutableStateOf("")

    private val _task = mutableStateOf("None")

    private val _completedDuration = mutableStateOf(0)

    private val _wasFocused = mutableStateOf(false)

    fun setTaskName(task:String){
        _task.value = task
    }

    fun setComplDuration(duration:Int){
        _completedDuration.value = duration
    }

    fun insertData(duration: Int){
        viewModelScope.launch {
            focusRepo.insertItem(FocusData(duration = duration, dateTime = _datetime.value, task = _task.value, completedDuration = _completedDuration.value))
        }
    }
}