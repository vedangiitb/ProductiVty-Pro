package com.example.productivtypro.Task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.R
import com.example.productivtypro.Task.taskItem.TaskItem
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Task.TaskDisplayViewModel
import com.example.productivtypro.ui.theme.container_type_2_color
import com.example.productivtypro.ui.theme.tilliumweb_extralight

@Composable
fun DisplayDaysTasks(viewModel: TaskDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory), dateString :String){
    val tasksDispUiState by viewModel.taskDispUiState.collectAsState()

    var showcompleted by remember {
        mutableStateOf(false)
    }

    var itemsList = tasksDispUiState.tasksList.filter { it.datetime == dateString}


    if (itemsList.isEmpty()) {
        Text(stringResource(R.string.notasks),modifier = Modifier.padding(5.dp),fontSize = 22.sp)
    }
    else{
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ) {
            Text("Today\'s Tasks", fontSize = 22.sp)
            Button(onClick = {showcompleted = !showcompleted}, colors = ButtonDefaults.buttonColors(containerColor = container_type_2_color)) {
                if (showcompleted) {
                    Text(stringResource(id = R.string.hidecompleted), fontFamily = tilliumweb_extralight)
                }
                else{
                    Text(stringResource(id = R.string.showCompleted), fontFamily = tilliumweb_extralight)
                }
            }
        }

        if (!showcompleted){
            itemsList = itemsList.filter { !it.isOver }
        }

        LazyColumn(modifier = Modifier.padding(start = 7.dp,end = 7.dp,top=5.dp, bottom = 5.dp)){
            items(items = itemsList){item ->
                TaskItem(item = item)
            }
        }
    }
}
