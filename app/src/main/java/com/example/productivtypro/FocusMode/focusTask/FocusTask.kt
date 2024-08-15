package com.example.productivtypro.FocusMode.focusTask

import android.view.Gravity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.HomePage.getTodaysDate
import com.example.productivtypro.R
import com.example.productivtypro.Task.AddNewTask
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Task.TaskDisplayViewModel


@Composable
fun TaskChooser(onDismiss: ()->Unit, viewModel: TaskDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory), focusTaskNameChange:(String, Int) -> Unit){
    Dialog(onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM)
        //get date
        val date = getTodaysDate()

        //get list of tasks for today's date
        val tasksDispUiState by viewModel.taskDispUiState.collectAsState()
        val itemsList = tasksDispUiState.tasksList.filter { it.datetime == date && !it.isOver}

        Column(modifier = Modifier.padding(start = 5.dp, top = 15.dp)) {

            if (itemsList.isEmpty()) {
                Text(stringResource(R.string.notask))
            } else {
                DisplayTasksFocus(itemsList,focusTaskNameChange)
            }
            Column(modifier = Modifier.padding(top=10.dp, bottom = 5.dp)) {
                AddNewTask(date = date, addPad = false)
            }
        }
    }
}