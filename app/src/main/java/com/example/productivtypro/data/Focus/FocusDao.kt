package com.example.productivtypro.data.Focus

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FocusDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FocusData)

    @Update
    suspend fun update(item: FocusData)

    @Delete
    suspend fun delete(item: FocusData)

    @Query("SELECT * from FocusSessionDataBase WHERE id = :id")
    fun getItem(id: Int): Flow<FocusData>

    @Query("SELECT * from FocusSessionDataBase ORDER BY dateTime ASC")
    fun getAllItems(): Flow<List<FocusData>>
}

