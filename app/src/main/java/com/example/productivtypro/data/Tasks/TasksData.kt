package com.example.productivtypro.data.Tasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TasksDataBase")
data class TasksData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val datetime: String = "",
    val isOver: Boolean = false,
    val priority: String = "Low",
    val hasReminder: Boolean = false,
    val reminderTime: String = "None",
    val repeat: String = "No",
    val subTaskList: String = "",
    val subTaskCompleteList: String = ""
)

