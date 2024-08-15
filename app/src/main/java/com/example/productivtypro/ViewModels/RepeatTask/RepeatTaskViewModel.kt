package com.example.productivtypro.ViewModels.RepeatTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivtypro.data.RepeatTasks.RepeatTasksData
import com.example.productivtypro.data.RepeatTasks.RepeatTasksRepo
import kotlinx.coroutines.launch

class RepeatTaskViewModel(private val repeatTaskRepo: RepeatTasksRepo) :ViewModel() {
    fun MakeTaskRepeated(taskId:Int,repeatString: String){
        viewModelScope.launch {
            repeatTaskRepo.insert(RepeatTasksData(taskId = taskId, taskString = repeatString))
        }
    }

    fun DelteRepeatingTask(taskId: Int){
        viewModelScope.launch {
            repeatTaskRepo.deleteRepeat(taskId)
        }
    }

    fun updateRepeat(taskId: Int,repeatString: String){
        viewModelScope.launch {
            repeatTaskRepo.updateRepeat(taskId,repeatString)
        }
    }
}