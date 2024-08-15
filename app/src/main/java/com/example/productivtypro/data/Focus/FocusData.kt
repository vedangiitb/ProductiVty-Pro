package com.example.productivtypro.data.Focus

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FocusSessionDataBase")
data class FocusData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val duration: Int = 1500,// In seconds
    val dateTime: String = "",
    val task : String = "None",
    val completedDuration: Int = 0, // In seconds
    val wasFocused: Boolean = false
)
