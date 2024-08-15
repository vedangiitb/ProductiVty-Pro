package com.example.productivtypro.ViewModels.Task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.data.Tasks.TasksRepo
import kotlinx.coroutines.launch

class TaskEntryViewModel(private val taskRepo: TasksRepo): ViewModel() {
    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _datetime = mutableStateOf("")
    val datetime: State<String> = _datetime

    fun setDateTime(datetime:String){
        _datetime.value = datetime
    }

    fun setInputName(name:String){
        _name.value = name
    }

    fun insertData(){
        val name = _name.value
        val date = _datetime.value
        if (name.isNotEmpty()){
            viewModelScope.launch {
                taskRepo.insertItem(TasksData(name = name, datetime = date, isOver = false))
            }
        }
    }
}