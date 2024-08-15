package com.example.productivtypro.data.RepeatTasks

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "RepeatTasks")
data class RepeatTasksData (
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int = 0,
    val taskId: Int,
    val taskString: String
)