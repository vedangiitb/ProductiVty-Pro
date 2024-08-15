package com.example.productivtypro.Task.taskItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.R
import com.example.productivtypro.Task.taskItem.dialogs.DeleteDialogBox
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.ui.theme.container_type_2_color
import com.example.productivtypro.ui.theme.tilliumweb_extralight

@Composable
fun TaskItem(item: TasksData, viewModel: TaskEditViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    var showDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 1.dp, bottom = 1.dp)
        ,
        colors = CardDefaults.cardColors(containerColor = container_type_2_color)) {
        Column(modifier = Modifier
            .clickable(onClick = { showDialog = true })
            .fillMaxSize()) {
            Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(3.dp)) {
                var isDone = item.isOver
                if (isDone){
                    Icon(painter = painterResource(R.drawable.check_circle), contentDescription = null,Modifier.clickable(onClick = {
                        isDone = !isDone
                        viewModel.updateIsOverStatus(taskId = item.id,isOver = isDone)
                    })
                        .padding(6.dp))
                }
                else{
                    Icon(painter = painterResource(R.drawable.unchecked_circle), contentDescription = null,Modifier.clickable(onClick = {
                        isDone = !isDone
                        viewModel.updateIsOverStatus(taskId = item.id,isOver = isDone)
                    })
                        .padding(6.dp))
                }
                var textStyle = TextDecoration.None
                if (item.isOver){
                    textStyle = TextDecoration.LineThrough
                }
                Text(text = item.name, fontFamily = tilliumweb_extralight, fontSize = 17.sp, textDecoration = textStyle, modifier = Modifier.weight(4f))
                IconButton(onClick = {showDialog = true },modifier = Modifier.weight(1f)) {
                    Icon(painter = painterResource(R.drawable.edit) ,contentDescription = "Edit Task Details")
                }
                IconButton(onClick = {showDeleteDialog = true },modifier = Modifier.weight(1f)) {
                    Icon(painter = painterResource(R.drawable.delete),contentDescription = "Delete Task")
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

    if (showDialog){
        TaskDialog(onDismiss = {showDialog=false}, taskItem = item,editviewModel = viewModel)
    }

    if (showDeleteDialog){
        DeleteDialogBox(editviewModel = viewModel, taskItem = item,onDismiss = {showDeleteDialog = false})
    }
}
