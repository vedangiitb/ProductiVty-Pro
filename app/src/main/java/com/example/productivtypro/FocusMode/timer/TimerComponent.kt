package com.example.productivtypro.FocusMode.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Focus.FocusViewModel
import com.example.productivtypro.ViewModels.Focus.TimerViewModel
import com.example.productivtypro.FocusMode.focusTask.IsCompletedDialog
import com.example.productivtypro.ui.theme.md_theme_dark_onTertiary


@Composable
fun TimerScreenContent(timerViewModel: TimerViewModel = viewModel(),
                       isSessionOver: () -> Unit,isTaskChosen:Boolean = false,
                       changeTaskName:()->Unit,
                       taskName:String) {
    val timerValue by timerViewModel.timer.collectAsState()
    val totalTime by timerViewModel.totalTime.collectAsState()

    TimerScreen(
        timerValue = timerValue,
        totalTime = totalTime,
        onStartClick = { timerViewModel.startTimer() },
        onStopClick = { timerViewModel.stopTimer() },
        setTotalTime = {minutes,seconds->
            timerViewModel.setTotalTime(minutes,seconds)
        },
        isTaskChosen,
        isSessionOver,
        changeTaskName = changeTaskName,
        taskName = taskName
    )
}

@Composable
fun TimerScreen(
    timerValue: Long,
    totalTime: Long,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit,
    setTotalTime: (Int,Int) -> Unit,
    isTaskChosen: Boolean,
    isSessionOver: () -> Unit,
    changeTaskName: () -> Unit,
    focusEntryViewModel: FocusViewModel = viewModel(factory = AppViewModelProvider.Factory),
    taskName: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            // Calculate the center and radius of the circle
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.minDimension / 3

            // Draw the background circle
            drawCircle(color = Color.LightGray, radius = radius, style = Stroke(15f))

            // Draw the progress arc based on the completion percentage
            drawArc(
                color = md_theme_dark_onTertiary ,
                startAngle = -90f,
                sweepAngle = -360f * timerValue/totalTime,
                useCenter = false,
                size = Size(radius * 2, radius * 2),
                topLeft = Offset(center.x - radius, center.y - radius),
                style = Stroke(20f)
            )
        }
        var isTimerRunning by remember { mutableStateOf(false) }

        var showDialog by remember { mutableStateOf(false) }

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
            Card(colors = CardDefaults.cardColors(containerColor = Color.Transparent), modifier = Modifier.clickable { showDialog = true }) {
                Text(text = (totalTime - timerValue).formatTime(), fontSize = 44.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            var showIsCompletedDialog by remember { mutableStateOf(false) }

            var changeTaskStatus ={}

            if (isTaskChosen){
                changeTaskStatus = isSessionOver
            }


            var newFn = {if (totalTime - timerValue != 0L && timerValue!=0L){
                if (isTaskChosen){
                    showIsCompletedDialog = true
                    focusEntryViewModel.setTaskName(taskName)
                }
                focusEntryViewModel.setComplDuration(timerValue.toInt())
                focusEntryViewModel.insertData(totalTime.toInt())
            }}

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                var clickFun = onStartClick
                if (timerValue>0L){
                    clickFun = onStopClick
                }

                Button(onClick = {
                    isTimerRunning = !isTimerRunning
                    clickFun()
                    newFn()
                }) {
                    if (totalTime - timerValue == 0L || timerValue==0L){
                        Text("Start Focus Session")
                    }
                    else{
                        Text("End Focus Session")
                    }
                }
            }
            if (showDialog) {
                TimerSetting(
                    onDismiss = { showDialog = false },
                    onTimerSet = {minute,second ->
                        showDialog = false
                        setTotalTime(minute,second)
                    }
                )
            }

            if (totalTime - timerValue == 1L){
                if (isTaskChosen){
                    showIsCompletedDialog = true
                    focusEntryViewModel.setTaskName(taskName)
                }
                focusEntryViewModel.setComplDuration(timerValue.toInt())
                focusEntryViewModel.insertData(totalTime.toInt())
            }

            if (showIsCompletedDialog){
                IsCompletedDialog(onDismiss = {showIsCompletedDialog =false}, markTaskDone = { changeTaskStatus(); changeTaskName()})
            }
        }
    }
}

