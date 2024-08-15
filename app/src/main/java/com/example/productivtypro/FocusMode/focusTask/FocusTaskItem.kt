package com.example.productivtypro.FocusMode.focusTask

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.productivtypro.data.Tasks.TasksData

@Composable
fun FocusTaskItem(tasksData: TasksData, focusTaskNameChange: (String, Int)->Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable(onClick = { focusTaskNameChange(tasksData.name, tasksData.id) })) {
        Text(tasksData.name,modifier = Modifier.padding(10.dp))
    }
}