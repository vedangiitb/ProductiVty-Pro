package com.example.productivtypro.data.Habit

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: HabitData)

    @Update
    suspend fun update(item: HabitData)

    @Delete
    suspend fun delete(item: HabitData)

    @Query("SELECT * FROM HabitDataBase")
    fun getAllHabits(): Flow<List<HabitData>>

    @Query("UPDATE HabitDataBase SET completionString =:newString WHERE id =:id")
    suspend fun updateCompletionString(id:Int,newString: String)

    @Query("UPDATE HabitDataBase SET name =:name WHERE id =:id ")
    suspend fun updateName(id:Int,name: String)

    @Query("UPDATE HabitDataBase SET endDate= :endDate WHERE id =:id")
    suspend fun updateEndDate(id:Int,endDate:String)

    @Query("UPDATE HabitDataBase SET completionString = :completionString WHERE id = :id")
    suspend fun updateComplString(id:Int,completionString: String)
}