package com.example.productivtypro.Task.taskItem.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.R
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.ui.theme.tilliumweb_extralight


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SubTasksCard(text:String, iconImage: Painter, taskItem: TasksData, taskEditViewModel: TaskEditViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    var isExpanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .clickable(onClick = { isExpanded = !isExpanded }),shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color(10,9,15,255))) {
        Column(modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(painter = iconImage, contentDescription = null)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text, fontSize = 18.sp, fontFamily = tilliumweb_extralight)
            }
            if (isExpanded) {
                if (taskItem.subTaskList.isEmpty()) {
                    Text(stringResource(R.string.nosubtasks),modifier = Modifier.padding(start = 20.dp,top = 10.dp))
                } else {
                    var subTaskList = taskItem.subTaskList.split(",")

                    for (i in subTaskList.indices) {
                        Row(modifier = Modifier
                            .padding(start = 15.dp, top = 1.dp, end = 1.dp, bottom = 1.dp)
                            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            //CheckBox
                            var isSubDone = taskItem.subTaskCompleteList[i].code
                            if (isSubDone==1){
                                Icon(painter = painterResource(R.drawable.check_circle), contentDescription = null,
                                    Modifier
                                        .clickable(onClick = {
                                            isSubDone = 0
                                            taskEditViewModel.markSubTaskDone(
                                                taskItem.id,
                                                i,
                                                taskItem.subTaskCompleteList,
                                                0
                                            )
                                        })
                                        .padding(6.dp))
                            }
                            else{
                                Icon(painter = painterResource(R.drawable.unchecked_circle), contentDescription = null,
                                    Modifier
                                        .clickable(onClick = {
                                            isSubDone = 1
                                            taskEditViewModel.markSubTaskDone(
                                                taskItem.id,
                                                i,
                                                taskItem.subTaskCompleteList,
                                                1
                                            )
                                        })
                                        .padding(6.dp))
                            }

                            //TaskName
                            Text(subTaskList[i])

                            //Delete SubTask
                            IconButton(onClick = { taskEditViewModel.deleteSubTask(taskItem.id,i,subTaskList,taskItem.subTaskCompleteList) }) {
                                Icon(painterResource(R.drawable.delete),contentDescription = null)
                            }
                        }
                    }
                }
                var subTaskName by remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current

                OutlinedTextField(value = subTaskName,label = { Text("Add a new subtask") },
                    onValueChange = {subTaskName = it},
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done, capitalization = KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }),
                    modifier = Modifier.fillMaxWidth(), singleLine = true,
                    trailingIcon = { IconButton(
                        onClick = {
                            taskEditViewModel.addSubTask(taskItem.id,subTaskName,taskItem.subTaskList,taskItem.subTaskCompleteList)
                            subTaskName = ""
                        }) {
                        Icon(imageVector = Icons.Default.Done ,contentDescription = "Add")
                    }
                    })

            }
        }
    }
}
