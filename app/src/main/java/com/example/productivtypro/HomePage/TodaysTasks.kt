package com.example.productivtypro.HomePage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.R
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Others.DateViewModel
import com.example.productivtypro.ViewModels.Task.TaskDisplayViewModel
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.Task.AddNewTask
import com.example.productivtypro.Task.taskItem.TaskItem
import com.example.productivtypro.ui.theme.tilliumweb_light

@Composable
fun getTodaysDate(viewModel: DateViewModel = viewModel()):String{
    val day: Int = viewModel.day.value
    val month: Int = viewModel.month.value
    val year: Int = viewModel.year.value
    val dateString = day.toString()+ '-'+ month.toString() +'-'+ year.toString()

    return dateString
}

@Composable
fun TodaysTasks(viewModel: TaskDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    //get date
    val date = getTodaysDate()

    //get list of tasks for today's date
    val tasksDispUiState by viewModel.taskDispUiState.collectAsState()
    val itemsList = tasksDispUiState.tasksList.filter { it.datetime == date && !it.isOver}

        Column(modifier = Modifier.padding(start = 5.dp, top = 15.dp)) {
            Text(stringResource(R.string.yourtasks), fontFamily = tilliumweb_light, fontSize = 20.sp)

            if (itemsList.isEmpty()) {
                Text(stringResource(R.string.noNew))
            } else {
                DisplayTasks(itemsList)
            }
            Column(modifier = Modifier.padding(top=10.dp, bottom = 5.dp)) {
                AddNewTask(date = date, addPad = false)
            }
        }
}

@Composable
fun DisplayTasks(itemsList:List<TasksData>){
    Column {
            Column(modifier = Modifier
                .fillMaxWidth())
            {
                itemsList.forEach{
                        item ->
                    TaskItem(item)
                }
            }
    }
}