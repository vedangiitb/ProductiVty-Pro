package com.example.productivtypro.FocusMode.focusTask

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.productivtypro.data.Tasks.TasksData

@Composable
fun DisplayTasksFocus(tasks:List<TasksData>, focusTaskNameChange: (String, Int)-> Unit){
    Column {
        tasks.forEach{
            FocusTaskItem(it,focusTaskNameChange)
        }
    }
}