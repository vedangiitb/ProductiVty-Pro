package com.example.productivtypro.data.RepeatTasks

class RepeatTasksRepo(private val repeatTasksDao: RepeatTasksDao) {

    suspend fun insert(item:RepeatTasksData) = repeatTasksDao.insert(item)

    suspend fun deleteRepeat(item:Int) = repeatTasksDao.deleteRepeat(item)

    suspend fun updateRepeat(id:Int,taskString:String) = repeatTasksDao.updateRepeat(id,taskString)

    fun selectAllTasks() = repeatTasksDao.selectAllTasks()
}