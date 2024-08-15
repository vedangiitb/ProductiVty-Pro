package com.example.productivtypro.FocusMode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productivtypro.R
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.FocusMode.focusTask.TaskChooser
import com.example.productivtypro.FocusMode.timer.TimerScreenContent

@Composable
fun FocusSession(navController: NavController,taskEditViewModel : TaskEditViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        Image(painter = painterResource(R.drawable.q_8), contentDescription = "Background image", modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.Crop)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            TopPart(navController)

            var taskChooseDialog by remember { mutableStateOf(false) }

            Button(onClick = { taskChooseDialog = true}) {
                Text(stringResource(R.string.chooseatask))
            }

            var focusTask by remember {
                mutableStateOf("No Task Chosen")
            }

            var taskId by remember {
                mutableStateOf(-1)
            }

            Text(focusTask)

            if (taskChooseDialog){
                TaskChooser(onDismiss = {taskChooseDialog = false},focusTaskNameChange = { name, id-> focusTask = name; taskId = id })
            }

            var isSessionComplete by remember {
                mutableStateOf(false)
            }


            var isTaskChosen = false
            if (taskId!=-1){
                isTaskChosen = true
            }

            TimerScreenContent(isSessionOver ={isSessionComplete = true}, isTaskChosen = isTaskChosen,changeTaskName ={focusTask = "Task Is Completed!"}, taskName = focusTask)

            if (isSessionComplete && taskId!=-1) run {
                taskEditViewModel.updateIsOverStatus(taskId, true)
            }
        }
    }
}