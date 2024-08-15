package com.example.productivtypro.Task.taskItem.cards

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Others.DateViewModel
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_extralight_it


@Composable
fun DateDialogCard(taskItem: TasksData, iconImage: Painter, taskEditViewModel: TaskEditViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    var currDate by remember { mutableStateOf(taskItem.datetime) }

    val dateViewModel: DateViewModel = viewModel()

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year:Int, month:Int, dayOfMonth:Int->
            currDate  = "$dayOfMonth-${month+1}-$year"
            taskEditViewModel.updateDate(taskItem.id,currDate)
        }, dateViewModel.year.value,dateViewModel.month.value-1,dateViewModel.day.value
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .clickable(onClick = { datePickerDialog.show() }),shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color(10,9,15,255))) {
        Row(verticalAlignment = Alignment.CenterVertically ,modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 10.dp)) {
            Icon(painter = iconImage,contentDescription = null)
            Spacer(modifier = Modifier.padding(5.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp)){
                Text("Due Date",fontSize = 18.sp,fontFamily = tilliumweb_extralight)
                Text(taskItem.datetime,fontSize = 18.sp,fontFamily = tilliumweb_extralight_it)
            }
        }
    }
}
