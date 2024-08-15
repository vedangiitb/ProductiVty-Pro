package com.example.productivtypro.Task.taskItem.cards

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.Notifications.requestNotificationAccess
import com.example.productivtypro.Notifications.setNotification
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_extralight_it


@Composable
fun ReminderCard(text:String, iconImage: Painter, taskItem: TasksData, taskEditViewModel: TaskEditViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    // Getting the hour and minute values from taskItem.reminderTime if there is any
    var hourVal = "0"
    var minuteVal = "0"
    if (taskItem.hasReminder){
        val reminderString = taskItem.reminderTime
        for (i in taskItem.reminderTime.indices){
            if (reminderString[i]==':'){
                hourVal = reminderString.substring(0,i)
                minuteVal = reminderString.substring(i+2)
                break
            }
        }
    }

    var hourValue by remember {
        mutableStateOf(hourVal.toInt())
    }

    var minuteValue by remember {
        mutableStateOf(minuteVal.toInt())
    }

    val context = LocalContext.current

    val timePicker = TimePickerDialog(
        context,
        { _: TimePicker, hour:Int, minute:Int->
            hourValue = hour
            minuteValue = minute
            if (!taskItem.hasReminder){
                taskEditViewModel.updateReminder(taskItem.id,true)
            }
            taskEditViewModel.updateReminderTime(taskItem.id,"$hour: $minute")
            setNotification(context,hour,minute,taskItem.id,taskItem.name)
        },
        hourValue,minuteValue,false,
    )


    if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            NotificationAccessDialog(
                onDismiss = { showDialog = false },
                onGrantAccess = {
                    requestNotificationAccess(context)
                }
            )
        } else {
            LaunchedEffect(showDialog) {
                showDialog = true
            }
        }
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .clickable(onClick = {
            timePicker.show()
        }),shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color(10,9,15,255))) {
        Row(verticalAlignment = Alignment.CenterVertically ,modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 10.dp, end = 15.dp)) {
            Icon(painter = iconImage,contentDescription = null)
            Spacer(modifier = Modifier.padding(5.dp))
            Row(modifier=  Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Text(text,fontSize = 18.sp,fontFamily = tilliumweb_extralight)
                Text(taskItem.reminderTime,fontSize = 18.sp,fontFamily = tilliumweb_extralight_it)
            }
        }
    }
}

@Composable
fun NotificationAccessDialog(
    onDismiss: () -> Unit,
    onGrantAccess: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Notification Access Required") },
        text = {
            Text("To set reminders, please grant notification access.")
        },
        confirmButton = {
            Button(
                onClick = {
                    onGrantAccess()
                    onDismiss()
                }
            ) {
                Text("Grant Access")
            }
        }
    )
}
