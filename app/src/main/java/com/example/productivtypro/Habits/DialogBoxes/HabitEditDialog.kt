package com.example.productivtypro.Habits.DialogBoxes

import android.app.DatePickerDialog
import android.view.Gravity
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.R
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Habit.HabitEditViewModel
import com.example.productivtypro.ViewModels.Others.DateViewModel
import com.example.productivtypro.data.Habit.HabitData
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_extralight_it
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun EditDialogBox(habitData: HabitData, onDismiss: ()-> Unit, habitEditViewModel: HabitEditViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    Dialog(onDismissRequest = { onDismiss()},
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM)

        var habitName by remember {
            mutableStateOf(habitData.name)
        }

        Card(colors = CardDefaults.cardColors(containerColor = Color(10,9,15,255))) {
            Column { 
                Card(colors = CardDefaults.cardColors(containerColor = Color(10,9,15,255))) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(value = habitName, onValueChange = {habitName = it},colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),singleLine = true)

                        IconButton(
                            onClick = {
                                habitEditViewModel.updateName(habitData.id,habitName)
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

                HabitDialogCard(text = stringResource(R.string.setReminder), onClick = {}, iconImage = painterResource(R.drawable.notifications))

                HabitDateDialogCard(habitItem = habitData, iconImage = painterResource(R.drawable.calender))
            }
        }

    }
}


fun dateDifference(date1: String, date2: String): Long {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    val parsedDate1: Date = dateFormat.parse(date1)
    val parsedDate2: Date = dateFormat.parse(date2)

    // Calculate the difference in milliseconds
    val differenceInMillis: Long = parsedDate2.time - parsedDate1.time

    // Convert milliseconds to days
    val differenceInDays: Long = differenceInMillis / (1000 * 60 * 60 * 24)

    return differenceInDays
}


@Composable
fun HabitDateDialogCard(habitItem: HabitData, iconImage:Painter, habitEditViewModel: HabitEditViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    var currDate by remember {mutableStateOf(habitItem.endDate)}

    val initialDate = habitItem.endDate

    val dateViewModel: DateViewModel = viewModel()

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year:Int, month:Int, dayOfMonth:Int->
            currDate  = "$dayOfMonth-${month+1}-$year"
            habitEditViewModel.updateEndDate(habitItem.id,currDate)
            habitEditViewModel.updateComplString(habitItem.id,
                dateDifference(initialDate,currDate),habitItem.completionString)
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
                Text(stringResource(R.string.changeEndDate),fontSize = 18.sp,fontFamily = tilliumweb_extralight)
                Text(habitItem.endDate,fontSize = 18.sp,fontFamily = tilliumweb_extralight_it)
            }
        }
    }
}

@Composable
fun HabitDialogCard(text:String,onClick:()-> Unit,iconImage: Painter){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .clickable(onClick = onClick),shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color(10,9,15,255))) {
        Row(verticalAlignment = Alignment.CenterVertically ,modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 10.dp)) {
            Icon(painter = iconImage,contentDescription = null)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text,fontSize = 18.sp,fontFamily = tilliumweb_extralight)
        }
    }
}