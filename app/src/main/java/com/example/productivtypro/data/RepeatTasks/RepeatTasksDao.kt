package com.example.productivtypro.data.RepeatTasks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepeatTasksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item:RepeatTasksData)

    @Query("DELETE  FROM RepeatTasks WHERE taskId = :id")
    suspend fun deleteRepeat(id:Int)

    @Query("UPDATE RepeatTasks SET taskString = :taskString WHERE taskId = :id")
    suspend fun updateRepeat(id:Int,taskString:String)

    @Query("SELECT * FROM RepeatTasks")
    fun selectAllTasks(): Flow<RepeatTasksData>
}