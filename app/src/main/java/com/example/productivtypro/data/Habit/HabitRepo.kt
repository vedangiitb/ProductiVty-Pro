package com.example.productivtypro.data.Habit



class HabitRepo(private val habitDao: HabitDao ) {
    suspend fun insertItem(item: HabitData) = habitDao.insert(item)

    suspend fun deleteItem(item: HabitData) = habitDao.delete(item)

    fun getAllHabits() = habitDao.getAllHabits()

    suspend fun updateCompletionString(id:Int,newString: String) = habitDao.updateCompletionString(id = id,newString = newString)

    suspend fun updateName(id:Int,newName:String) = habitDao.updateName(id=id, name = newName)

    suspend fun updateEndDate(id:Int,endDate:String) = habitDao.updateEndDate(id = id,endDate = endDate)

    suspend fun updateComplString(id:Int,completionString: String) = habitDao.updateComplString(id = id,completionString = completionString)
}