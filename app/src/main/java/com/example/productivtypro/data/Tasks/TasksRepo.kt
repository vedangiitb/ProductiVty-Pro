package com.example.productivtypro.data.Tasks

import kotlinx.coroutines.flow.Flow

class TasksRepo(private val tasksDao: TasksDao) {
    fun getAllTasksStream(): Flow<List<TasksData>> = tasksDao.getAllItems()

    suspend fun insertItem(item: TasksData) = tasksDao.insert(item)

    suspend fun deleteItem(item: TasksData) = tasksDao.delete(item)

    fun getItem(id: Int) = tasksDao.getItem(id)

    fun getMaxId() = tasksDao.getMaxId()

    suspend fun updateIsOverStatus(taskId: Int, isOver: Boolean) = tasksDao.updateIsOverStatus(taskId = taskId,isOver = isOver)

    suspend fun updateName(taskId: Int,name: String) = tasksDao.updateTaskName(taskId = taskId, name = name)

    suspend fun updatePriority(taskId: Int,priority:String) = tasksDao.updatePriority(taskId=taskId, priority = priority)

    suspend fun updateDate(taskId: Int,date:String) = tasksDao.updateDate(taskId,date)

    suspend fun updateReminder(taskId: Int,reminderUpdate: Boolean) = tasksDao.updateReminder(taskId,reminderUpdate)

    suspend fun updateReminderTime(taskId: Int,reminderTime:String) = tasksDao.updateReminderTime(taskId, reminderTime)

    suspend fun updateRepFreq(taskId: Int,repeat:String) = tasksDao.updateRepFreq(taskId,repeat)

    suspend fun updateSubTasksList(taskId: Int,subTaskList:String) = tasksDao.updateSubTasksList(taskId,subTaskList)

    suspend fun updateSubTaskCompleteList(taskId: Int,subTaskCompleteList:String) = tasksDao.updateSubTaskCompleteList(taskId, subTaskCompleteList)

    fun getOverNotOver() = tasksDao.getOverNotOver()
}