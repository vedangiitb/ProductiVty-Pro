package com.example.productivtypro.data.Tasks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: TasksData)

    @Update
    suspend fun update(item: TasksData)

    @Delete
    suspend fun delete(item: TasksData)

    @Query("SELECT * from TasksDataBase WHERE id = :id")
    fun getItem(id: Int): Flow<TasksData>

    @Query("SELECT MAX(id) from TasksDataBase")
    fun getMaxId(): Int?

    @Query("SELECT * from TasksDataBase ORDER BY isOver, datetime ASC")
    fun getAllItems(): Flow<List<TasksData>>

    @Query("SELECT * from TasksDataBase WHERE datetime = :datetime ORDER BY isOver")
    fun getTaskForDate(datetime:String): Flow<List<TasksData>>

    @Query("UPDATE TasksDataBase SET isOver = :isOver WHERE id = :taskId")
    suspend fun updateIsOverStatus(taskId: Int, isOver: Boolean)

    @Query("UPDATE TasksDataBase SET name = :name WHERE id = :taskId")
    suspend fun updateTaskName(taskId:Int,name:String)

    @Query("UPDATE TasksDataBase SET priority = :priority WHERE id = :taskId")
    suspend fun updatePriority(taskId: Int,priority:String)


    @Query("UPDATE TasksDataBase SET datetime= :datetime WHERE id =:taskId")
    suspend fun updateDate(taskId: Int,datetime: String)

    @Query("UPDATE TasksDataBase SET hasReminder = :reminderUpdate WHERE id = :taskId")
    suspend fun updateReminder(taskId: Int,reminderUpdate: Boolean)

    @Query("UPDATE TasksDataBase SET reminderTime = :reminderTime WHERE id =:taskId")
    suspend fun updateReminderTime(taskId: Int,reminderTime:String)

    @Query("UPDATE TasksDataBase SET repeat = :repeatFreq WHERE id = :taskId")
    suspend fun updateRepFreq(taskId: Int,repeatFreq:String)

    @Query("UPDATE TasksDataBase SET subTaskList =:subTaskList WHERE id = :taskId")
    suspend fun updateSubTasksList(taskId: Int,subTaskList:String)

    @Query("UPDATE TasksDataBase SET subTaskCompleteList= :subTaskCompleteList WHERE id = :taskId")
    suspend fun updateSubTaskCompleteList(taskId: Int,subTaskCompleteList:String)

    @Query("SELECT datetime,COUNT(*) AS totalTasks, (SELECT COUNT(*) FROM tasksdatabase t2 WHERE t2.isOver = 1 AND t2.datetime = t1.datetime) AS overcount,  (SELECT COUNT(*) FROM tasksdatabase t3 WHERE t3.isOver = 0 AND t3.datetime = t1.datetime) AS NotOverCount FROM tasksdatabase t1 GROUP BY datetime")
    fun getOverNotOver(): Flow<List<TaskSummary>>
}