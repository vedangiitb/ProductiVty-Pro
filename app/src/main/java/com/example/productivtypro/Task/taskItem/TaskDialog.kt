package com.example.productivtypro.Task.taskItem

import android.view.Gravity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.example.productivtypro.R
import com.example.productivtypro.Task.taskItem.cards.DateDialogCard
import com.example.productivtypro.Task.taskItem.cards.PriorityCard
import com.example.productivtypro.Task.taskItem.cards.ReminderCard
import com.example.productivtypro.Task.taskItem.cards.SubTasksCard
import com.example.productivtypro.Task.taskItem.cards.TaskDialogCard
import com.example.productivtypro.Task.taskItem.dialogs.DeleteDialogBox
import com.example.productivtypro.Task.taskItem.dialogs.RepeatDialog
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_light

@Composable
fun TaskDialog(onDismiss: () -> Unit, taskItem: TasksData, editviewModel: TaskEditViewModel){
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showRepeatDialog by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = { onDismiss()},
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM)

        var name by remember {
            mutableStateOf(taskItem.name)
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            colors = CardDefaults.cardColors(containerColor  = MaterialTheme.colorScheme.background)) {
            Text(stringResource(R.string.edittaskdetails),fontSize = 20.sp, fontFamily = tilliumweb_light, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))

            Column(modifier = Modifier
                .padding(bottom = 2.dp)
                .verticalScroll(
                    rememberScrollState()
                )
                .fillMaxWidth(),
                ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 5.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        var isDone = taskItem.isOver
                        if (isDone){
                            Icon(painter = painterResource(R.drawable.check_circle), contentDescription = null,
                                Modifier
                                    .clickable(onClick = {
                                        isDone = !isDone
                                        editviewModel.updateIsOverStatus(
                                            taskId = taskItem.id,
                                            isOver = isDone
                                        )
                                    })
                                    .padding(6.dp))
                        }
                        else{
                            Icon(painter = painterResource(R.drawable.unchecked_circle), contentDescription = null,
                                Modifier
                                    .clickable(onClick = {
                                        isDone = !isDone
                                        editviewModel.updateIsOverStatus(
                                            taskId = taskItem.id,
                                            isOver = isDone
                                        )
                                    })
                                    .padding(6.dp))
                        }

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            modifier = Modifier.weight(5f),
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = tilliumweb_extralight,
                                textAlign = TextAlign.Left
                            ),
                            singleLine = true
                        )
                        IconButton(
                            onClick = {
                                editviewModel.updateName(taskItem.id, name)
                                onDismiss()
                            },
                            modifier = Modifier.weight(0.8f)
                        ) {
                            Icon(imageVector = Icons.Default.Done, contentDescription = null)
                        }
                        IconButton(
                            onClick = {
                                onDismiss()
                            },
                            modifier = Modifier.weight(0.8f)
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                }

                DateDialogCard(taskItem =taskItem, iconImage = painterResource(R.drawable.calender))

                ReminderCard(text = stringResource(R.string.setReminder), iconImage = painterResource(R.drawable.notifications),taskItem)

                PriorityCard(text = stringResource(R.string.priority) ,onClick = {}, iconImage = painterResource(R.drawable.priority), taskEditViewModel = editviewModel,taskItem = taskItem)

                SubTasksCard(text = stringResource(R.string.addsubtask),iconImage = painterResource(R.drawable.subtask) ,taskItem= taskItem)

                TaskDialogCard(text = stringResource(R.string.repeattask), onClick = { showRepeatDialog = true }, iconImage = painterResource(R.drawable.event_repeat) )

                TaskDialogCard(text = stringResource(R.string.deleteTask), onClick = { showDeleteDialog = true }, iconImage = painterResource(R.drawable.delete) )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = { onDismiss() },modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(stringResource(R.string.done))
            }
        }
    }

    if (showDeleteDialog){
        DeleteDialogBox(editviewModel = editviewModel, taskItem = taskItem,onDismiss = {showDeleteDialog = false})
    }

    if(showRepeatDialog){
        RepeatDialog(onDismiss = {showRepeatDialog = false}, taskItem = taskItem)
    }
}


