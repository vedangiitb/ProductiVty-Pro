package com.example.productivtypro.Habits

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productivtypro.Notifications.setHabitNotification
import com.example.productivtypro.R
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Habit.HabitEntryViewModel
import com.example.productivtypro.ViewModels.Others.DateViewModel
import com.example.productivtypro.ui.theme.md_theme_dark_primary
import com.example.productivtypro.ui.theme.md_theme_dark_secondary
import com.example.productivtypro.ui.theme.tilliumweb_bold
import com.example.productivtypro.ui.theme.tilliumweb_extralight_it
import com.example.productivtypro.ui.theme.tilliumweb_light

@Composable
fun AddNewHabits(navController: NavController,habitEntryViewModel: HabitEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .verticalScroll(rememberScrollState()) ) {
        //Habit Name
        var habitName by remember {
            mutableStateOf("")
        }

        var hasReminder by remember { mutableStateOf(false) }

        val context = LocalContext.current

        var reminderTime by remember { mutableStateOf("No Reminder") }

        var hourValue by remember {
            mutableStateOf(0)
        }

        var minuteValue by remember {
            mutableStateOf(0)
        }

        Text(stringResource(id = R.string.makenewhabit), fontSize = 26.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(40.dp))

        Text(stringResource(id = R.string.namehabit), fontSize = 20.sp, fontFamily = tilliumweb_light, textDecoration = TextDecoration.Underline)
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = habitName,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done, capitalization = KeyboardCapitalization.Sentences),
            onValueChange ={habitName = it},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp))

        Spacer(modifier = Modifier.height(30.dp))

        val dateViewModel:DateViewModel = viewModel()

        //Start Date
        var currDate by remember {mutableStateOf(dateViewModel.getTodayDate())}

        // End Date
        var endDate by remember { mutableStateOf(dateViewModel.getTomorrowDate()) }

        val datePickerDialogStart = DatePickerDialog(
            LocalContext.current,
            { _: DatePicker,year:Int, month:Int, dayOfMonth:Int->
                currDate  = "$dayOfMonth-${month+1}-$year"
            }, dateViewModel.year.value,dateViewModel.month.value-1,dateViewModel.day.value
        )

        val datePickerDialogEnd = DatePickerDialog(
            LocalContext.current,
            { _: DatePicker,year:Int, month:Int, dayOfMonth:Int->
                endDate  = "$dayOfMonth-${month+1}-$year"
            }, dateViewModel.year.value,dateViewModel.month.value-1,dateViewModel.day.value
        )

        Text("Enter Start and End Dates", fontSize = 20.sp, fontFamily = tilliumweb_light, textDecoration = TextDecoration.Underline)
        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)){
            Card(Modifier.clickable(onClick = {datePickerDialogStart.show()})){
                Text(currDate, modifier = Modifier.padding(6.dp), fontFamily = tilliumweb_extralight_it)
            }

            Spacer(modifier = Modifier.width(10.dp))

            Card(Modifier.clickable(onClick = {datePickerDialogEnd.show()})){
                Text(endDate, modifier = Modifier.padding(6.dp), fontFamily = tilliumweb_extralight_it)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text("Choose Frequency", fontSize = 20.sp, fontFamily = tilliumweb_light, textDecoration = TextDecoration.Underline)
        Spacer(modifier = Modifier.height(10.dp))

        //Frequency
        var frequency by remember { mutableStateOf("Daily") }

        var someSelectedDays by remember { mutableStateOf("1000000") }

        var showDaySelectOption by remember { mutableStateOf(false) }

        var showFreqSelectOption by remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxWidth()){
            val showTick = mutableListOf(false,false,false)

            if (frequency== "Daily"){
                showTick[0] = true
            }
            else if(frequency.substring(0,6)=="Weekly"){
                showTick[1] = true
            }
            else{
                showTick[2] = true
            }

            Card(border = BorderStroke(2.dp, Color.DarkGray),colors = CardDefaults.cardColors(containerColor = Color.Transparent)) {
                Row(modifier = Modifier.padding(5.dp)){
                    if (showTick[0]){
                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
                    }
                    Text(stringResource(R.string.daily),modifier = Modifier
                        .clickable(onClick = {
                            frequency = "Daily"
                            showDaySelectOption = false
                            showFreqSelectOption = false
                        }))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            var numtimes by remember {mutableStateOf(1)}

            Column(modifier = Modifier.fillMaxWidth()){
                Card(border = BorderStroke(2.dp, Color.DarkGray),colors = CardDefaults.cardColors(containerColor = Color.Transparent)){
                    Row(modifier = Modifier.padding(5.dp)){
                        if (showTick[1]){
                            Icon(imageVector = Icons.Default.Done, contentDescription = null)
                        }
                        Column {
                            Text(stringResource(R.string.numberOfDaysWeek), modifier = Modifier
                                .clickable(onClick = {
                                    frequency = "Weekly$numtimes"
                                    showDaySelectOption = false
                                    showFreqSelectOption = true
                                }) )
                        }
                    }
                }
                if (showFreqSelectOption){
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically ) {
                        IconButton(onClick = {numtimes = maxOf(numtimes-1, 1)
                            frequency = "Weekly$numtimes"}) {
                            Icon(painter = painterResource(id = R.drawable.remove), contentDescription = null)
                        }
                        Text(numtimes.toString(), fontSize = 11.sp)
                        IconButton(onClick = {numtimes= minOf(7,numtimes+1)
                            frequency = "Weekly$numtimes"}) {
                            Icon(imageVector = Icons.Default.Add,contentDescription = null)
                        }
                    }
                }
                else{
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier.fillMaxWidth()){
                Card(border = BorderStroke(2.dp, Color.DarkGray),colors = CardDefaults.cardColors(containerColor = Color.Transparent)){
                    Row(modifier = Modifier.padding(5.dp)){
                        if (showTick[2]){
                            Icon(imageVector = Icons.Default.Done, contentDescription = null)
                        }
                        Column(modifier = Modifier) {
                            Text(stringResource(R.string.someDays), modifier = Modifier
                                .clickable(onClick = {
                                    frequency = "Some$someSelectedDays"
                                    showDaySelectOption = true
                                    showFreqSelectOption = false
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
                                            frequency = "Some$someSelectedDays"
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
                                        frequency = "Some$someSelectedDays"
                                    }))
                                {
                                    Text(daysList[i],fontSize = 11.sp,modifier = Modifier.padding(1.5.dp))
                                }
                            }
                        }
                    }
                }
                else{
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("Type of Habit", fontSize = 20.sp, fontFamily = tilliumweb_light, textDecoration = TextDecoration.Underline)
        Spacer(modifier = Modifier.height(10.dp))


        //Type
        var type by remember {mutableStateOf("Bool")}
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Card(modifier = Modifier.clickable(onClick = {type = "Num"}),border = BorderStroke(2.dp, Color.DarkGray),colors = CardDefaults.cardColors(Color.Transparent)){
                Row(verticalAlignment = Alignment.CenterVertically){
                    if (type == "Num") {
                        Icon(Icons.Default.Done, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(3.dp))
                    Text("It will be Numerical", modifier = Modifier.padding(6.dp))
                }
            }

            Card(modifier = Modifier.clickable(onClick = {type="Bool"}),colors = CardDefaults.cardColors(Color.Transparent),border = BorderStroke(2.dp, Color.DarkGray)){
                Row(verticalAlignment = Alignment.CenterVertically){
                    if (type == "Bool") {
                        Icon(Icons.Default.Done, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(3.dp))
                    Text("It will be YES or NO", modifier = Modifier.padding(6.dp))
                }
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(stringResource(R.string.setReminder),fontSize = 20.sp, fontFamily = tilliumweb_light, textDecoration = TextDecoration.Underline)

            val timePicker = TimePickerDialog(
                context,
                { _: TimePicker, hour:Int, minute:Int->
                    hourValue = hour
                    minuteValue = minute
                    hasReminder = true
                    reminderTime = "$hourValue:$minuteValue"
                },
                hourValue,minuteValue,false,
            )

            Text(reminderTime,Modifier.clickable(onClick = {timePicker.show()}), fontFamily = tilliumweb_extralight_it)

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center ){
            Button(onClick = {
                habitEntryViewModel.setHabitName(habitName)
                habitEntryViewModel.setStartDate(currDate)
                habitEntryViewModel.setEndDate(endDate)
                habitEntryViewModel.setFrequency(frequency)
                habitEntryViewModel.upDateComplString(currDate,endDate,frequency)
                habitEntryViewModel.updateType(type)
                habitEntryViewModel.updateReminder(hasReminder = hasReminder, reminderTime = reminderTime)
                setHabitNotification(context, hour = hourValue, minute = minuteValue, endDate = endDate,id=0, name = habitName)
                habitEntryViewModel.insertData()
                navController.popBackStack()
            }) {
                Text(stringResource(R.string.addNewHabit))
            }

        }


    }
}