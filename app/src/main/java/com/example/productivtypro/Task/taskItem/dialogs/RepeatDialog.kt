package com.example.productivtypro.Task.taskItem.dialogs

import android.view.Gravity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.R
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.RepeatTask.RepeatTaskViewModel
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.ui.theme.md_theme_dark_primary
import com.example.productivtypro.ui.theme.md_theme_dark_secondary
import com.example.productivtypro.ui.theme.tilliumweb_bold

@Composable
fun RepeatDialog(onDismiss: () -> Unit,
                 taskEditViewModel: TaskEditViewModel = viewModel(factory = AppViewModelProvider.Factory),
                 taskItem:TasksData,
                 repeatTaskViewModel: RepeatTaskViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(dismissOnBackPress = true,
        dismissOnClickOutside = true,
        usePlatformDefaultWidth = false)
    ) {
        val taskId = taskItem.id
        var isRepeat by remember {
            mutableStateOf(false)
        }

        if (taskItem.repeat!="No"){
            isRepeat = true
        }

        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM)

        //Frequency
        var frequency by remember { mutableStateOf(taskItem.repeat) }

        var someSelectedDays by remember { mutableStateOf("1000000") }

        var showDaySelectOption by remember { mutableStateOf(false) }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 20.dp, bottom = 20.dp)) {
            Text(stringResource(R.string.repeattask))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp), horizontalArrangement = Arrangement.SpaceBetween){
                val showTick = mutableListOf(false,false)

                if (frequency== "1111111"){
                    showTick[0] = true
                }
                else if (frequency!="No"){
                    showTick[1] = true
                }

                Card(border = BorderStroke(2.dp, Color.DarkGray)) {
                    Row(modifier = Modifier.padding(5.dp)){
                        if (showTick[0]){
                            Icon(imageVector = Icons.Default.Done, contentDescription = null)
                        }
                        Text(
                            stringResource(R.string.daily),modifier = Modifier
                            .clickable(onClick = {
                                frequency = "1111111"
                                showDaySelectOption = false
                            }
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column(modifier = Modifier.fillMaxWidth()){
                    Card(border = BorderStroke(2.dp, Color.DarkGray)){
                        Row(modifier = Modifier.padding(5.dp)){
                            if (showTick[1]){
                                Icon(imageVector = Icons.Default.Done, contentDescription = null)
                            }
                            Column(modifier = Modifier) {
                                Text(
                                    stringResource(R.string.someDays), modifier = Modifier
                                    .clickable(onClick = {
                                        frequency = someSelectedDays
                                        showDaySelectOption = true
                                    }))
                            }
                        }
                    }
                    if (showDaySelectOption){
                        Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier
                            .padding(top = 5.dp, start = 30.dp, end = 30.dp)
                            .fillMaxWidth()) {
                            val daysList = listOf("Mon","Tue","Wed","Thu","Fri","Sat","Sun")
                            for (i in daysList.indices){
                                if (someSelectedDays[i]=='1'){
                                    Box(
                                        modifier = Modifier
                                            .size(26.dp)
                                            .background(
                                                color = md_theme_dark_primary,
                                                shape = RoundedCornerShape(28.dp)
                                            )
                                            .clickable(onClick = {
                                                someSelectedDays = someSelectedDays.substring(
                                                    0,
                                                    i
                                                ) + '0' + someSelectedDays.substring(
                                                    minOf(
                                                        i + 1,
                                                        someSelectedDays.length
                                                    ), someSelectedDays.length
                                                )
                                                frequency = someSelectedDays
                                            })
                                    ){
                                        Text(daysList[i],fontFamily = tilliumweb_bold,fontSize = 11.sp,modifier = Modifier.padding(1.5.dp))
                                    }
                                }
                                else{
                                    Box(modifier = Modifier
                                        .size(26.dp)
                                        .background(
                                            color = md_theme_dark_secondary,
                                            shape = RoundedCornerShape(28.dp)
                                        )
                                        .clickable(onClick = {
                                            someSelectedDays = someSelectedDays.substring(
                                                0,
                                                i
                                            ) + '1' + someSelectedDays.substring(
                                                minOf(
                                                    i + 1,
                                                    someSelectedDays.length
                                                ), someSelectedDays.length
                                            )
                                            frequency = someSelectedDays
                                        }))
                                    {
                                        Text(daysList[i],fontSize = 11.sp,modifier = Modifier.padding(1.5.dp))
                                    }
                                }
                            }
                        }
                    }
                    else{
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
            Button(onClick = {
                taskEditViewModel.updateRepeat(taskId,frequency)
                if (!isRepeat){
                    repeatTaskViewModel.MakeTaskRepeated(taskId,frequency)
                }
                else{
                    repeatTaskViewModel.updateRepeat(taskId,frequency)
                }
                onDismiss()}
            ) {
                Text(stringResource(id = R.string.done))
            }
        }
    }
}
