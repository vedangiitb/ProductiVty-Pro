package com.example.productivtypro.data.Tasks

data class TaskSummary(
    val datetime: String,  // Adjust the type according to your actual database schema
    val totalTasks: Int,
    val overcount: Int,
    val NotOverCount : Int
)