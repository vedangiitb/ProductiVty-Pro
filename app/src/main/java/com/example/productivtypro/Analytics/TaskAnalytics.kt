package com.example.productivtypro.Analytics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.R
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Task.TaskDisplayViewModel
import com.example.productivtypro.ui.theme.tilliumweb_extralight

@Composable
fun TaskAnalytics(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(id = R.string.taskanalytics), fontSize = 22.sp, fontFamily = tilliumweb_extralight)
        // Task Completion Analytics
        ComputeTaskAnalytics()
        // Priority Analysis

        // Reminders Usage

        // Impact of reminders on tasks completion

    }
}


@Composable
fun TaskCompletionAnalytics(){

}

@Composable
fun PriorityAnalytics(){

}


@Composable
fun RemindersAnalytics(){

}

@Composable
fun ReminderImpactAnalytics(){

}

@Composable
fun ComputeTaskAnalytics(viewModel: TaskDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    val tasksDoneUiState by viewModel.taskDoneFilterUiState.collectAsState()

    val completed = tasksDoneUiState.tasksList

}