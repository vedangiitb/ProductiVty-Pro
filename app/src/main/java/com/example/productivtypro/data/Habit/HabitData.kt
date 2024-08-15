package com.example.productivtypro.data.Habit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HabitDataBase")
data class HabitData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val completionString: String = "",
    val frequency: String = "Daily",
    val type: String = "Bool",
    val hasReminder: Boolean = false,
    val reminderTime: String = ""
)