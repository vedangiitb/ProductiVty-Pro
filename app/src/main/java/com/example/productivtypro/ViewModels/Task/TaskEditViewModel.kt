package com.example.productivtypro.ViewModels.Task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.data.Tasks.TasksRepo
import kotlinx.coroutines.launch

class TaskEditViewModel(private val tasksRepo: TasksRepo):ViewModel() {

    fun updatePriority(taskId: Int,priority:String){
        viewModelScope.launch {
            tasksRepo.updatePriority(taskId = taskId, priority = priority)
        }
    }

    fun updateName(taskId: Int,name: String){
        viewModelScope.launch {
            tasksRepo.updateName(taskId,name)
        }
    }

    fun updateReminder(taskId: Int,reminderUpdate:Boolean){
        viewModelScope.launch {
            tasksRepo.updateReminder(taskId,reminderUpdate)
        }
    }

    fun updateReminderTime(taskId: Int,reminderTime:String){
        viewModelScope.launch {
            tasksRepo.updateReminderTime(taskId,reminderTime)
        }
    }

    fun updateDate(taskId: Int,date:String){
        viewModelScope.launch {
            tasksRepo.updateDate(taskId,date)
        }
    }

    fun updateIsOverStatus(taskId: Int, isOver: Boolean) {
        viewModelScope.launch {
            tasksRepo.updateIsOverStatus(taskId, isOver)
        }
    }

    fun deleteTask(item:TasksData){
        viewModelScope.launch {
            tasksRepo.deleteItem(item)
        }
    }

    fun updateRepeat(taskId: Int,repeat: String){
        viewModelScope.launch {
            tasksRepo.updateRepFreq(taskId,repeat)
        }
    }

    fun addSubTask(taskId: Int,subTask:String,subTasksString:String,subTasksDoneString:String){
        if (subTask.isEmpty()){
            return
        }

        var newSubTaskString = ""
        var newSubTasksDoneString = ""
        newSubTaskString = if (subTasksString.isNotEmpty()){
            "$subTasksString,$subTask"
        } else{
            subTask
        }
        newSubTasksDoneString = if(subTasksString.isNotEmpty()){
            subTasksDoneString + "0"
        }else{
            "0"
        }
        viewModelScope.launch {
            tasksRepo.updateSubTaskCompleteList(taskId,newSubTasksDoneString)
            tasksRepo.updateSubTasksList(taskId,newSubTaskString)
        }
    }

    fun markSubTaskDone(taskId: Int,index:Int,subTasksDoneString: String,newVal:Int){
        var newsubTaskDoneString = subTasksDoneString

        if (index<subTasksDoneString.length){
            val stringBuilder = StringBuilder(newsubTaskDoneString)
            stringBuilder.setCharAt(index, newVal.toChar())
            newsubTaskDoneString = stringBuilder.toString()

            viewModelScope.launch {
                tasksRepo.updateSubTaskCompleteList(taskId,newsubTaskDoneString)
            }
        }
    }

    fun deleteSubTask(taskId: Int,index: Int,subTasksString:List<String>,subTasksDoneString:String){
        var newSubTasksList = subTasksString.toMutableList()
        newSubTasksList.removeAt(index)
        var newSubTaskDoneList = subTasksDoneString.substring(0,index) + subTasksDoneString.substring(index+1)

        viewModelScope.launch {
            tasksRepo.updateSubTasksList(taskId,newSubTasksList.joinToString(","))
            tasksRepo.updateSubTaskCompleteList(taskId,newSubTaskDoneList)
        }
    }

}