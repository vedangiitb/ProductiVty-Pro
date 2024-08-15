package com.example.productivtypro.workers

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.data.RepeatTasks.RepeatTaskInst
import com.example.productivtypro.data.RepeatTasks.RepeatTasksRepo
import com.example.productivtypro.data.Tasks.TaksDataInst
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.data.Tasks.TasksRepo
import kotlinx.coroutines.flow.toList
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


class TaskRepeat(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context,workerParams){
    private val repeatTasksRepo: RepeatTasksRepo
    private val tasksRepo: TasksRepo

    init {
        val appDatabaseRepeat = RepeatTaskInst.getDatabase(context)
        val repeatTasksDao = appDatabaseRepeat.repeattaskDao()
        repeatTasksRepo = RepeatTasksRepo(repeatTasksDao)

        val appDatabase = TaksDataInst.getDatabase(context)
        val TasksDao = appDatabase.itemDao()
        tasksRepo = TasksRepo(TasksDao)
    }

    override suspend fun doWork(): Result {
        // Get all the task Ids from repeatTasksData

        var tasksIds = getWorkIds(repeatTasksRepo).toList()
        val currentDate = getCurrentDate()
        var maxId = tasksRepo.getMaxId()?:0

        for (id in tasksIds){
            val task = tasksRepo.getItem(id).toList()
            if (task.isNotEmpty()){
                val taskData = TasksData(id=maxId+1,
                    name = task[0].name,
                    datetime = currentDate,
                    isOver = task[0].isOver,
                    priority = task[0].priority,
                    hasReminder = task[0].hasReminder,
                    reminderTime = task[0].reminderTime,
                    repeat = task[0].repeat,
                    subTaskList = task[0].subTaskList,
                    subTaskCompleteList = task[0].subTaskCompleteList
                )
                maxId+=1
                tasksRepo.insertItem(taskData)
            }
        }

        return Result.success()
    }

    private suspend fun getWorkIds(repeatTasksRepo: RepeatTasksRepo): List<Int>{
        var tasks =  repeatTasksRepo.selectAllTasks().toList()
        var ids = mutableListOf<Int>()
        // Get today's day
        val days = listOf(Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY,Calendar.THURSDAY,Calendar.FRIDAY,Calendar.SATURDAY,Calendar.SUNDAY)
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        val ind = days.indexOf(day)

        for (i in tasks){
            if (i.taskString[ind]=='1'){
                ids.add(i.taskId)
            }
        }

        return ids
    }


    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return currentDate.format(formatter)
    }
}
