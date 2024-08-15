package com.example.productivtypro.Habits

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.productivtypro.R
import com.example.productivtypro.data.Habit.HabitData
import com.example.productivtypro.Habits.DialogBoxes.EditDialogBox
import com.example.productivtypro.Habits.DialogBoxes.HabitDayBoolDialog
import com.example.productivtypro.Habits.DialogBoxes.HabitDayNumDialog
import com.example.productivtypro.Habits.DialogBoxes.deleteDialog
import com.example.productivtypro.ui.theme.tilliumweb_bold
import com.example.productivtypro.ui.theme.tilliumweb_bold_it
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.color.KalendarColors
import kotlinx.datetime.LocalDate

@Composable
fun DailyHabitContent(habitData: HabitData){
    var showDeleteDialogBox by remember { mutableStateOf(false) }

    var showEditDialogBox by remember { mutableStateOf(false) }

    val dayString = habitData.frequency.substring(4,habitData.frequency.length)
    val allDaysList = listOf("MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY")
    val activeDays = mutableSetOf<String>()

    for (day in dayString.indices){
        if (dayString[day]=='1'){
            activeDays.add(allDaysList[day])
        }
    }

    Kalendar(currentDay = null, kalendarType = KalendarType.Oceanic, dayContent = {
        DayHighlight(
        date =it,
        day = it.dayOfWeek.toString(),
        startDate = habitData.startDate,
        completionString = habitData.completionString,
        habitId = habitData.id,
        habitType = habitData.type,
        habitFreq = habitData.frequency,
        activeDays = activeDays
    )
    }, kalendarColors = KalendarColors(List(12){ KalendarColor(backgroundColor = Color.Transparent, dayBackgroundColor = Color.Blue, headerTextColor = Color.Gray) }))

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {}) {
            Text("View Report", fontFamily = tilliumweb_extralight )
        }

        Row{
            IconButton(onClick = {showEditDialogBox = true}) {
                Icon(painter = painterResource(R.drawable.edit),contentDescription = null)
            }
            IconButton(onClick = {showDeleteDialogBox = true}) {
                Icon(painter = painterResource(R.drawable.delete),contentDescription = null)
            }
        }


    }
    if (showDeleteDialogBox){
        deleteDialog(onDismiss = {showDeleteDialogBox = false}, habitData = habitData)
    }

    if (showEditDialogBox){
        EditDialogBox(habitData = habitData,onDismiss = {showEditDialogBox = false})
    }

}

@Composable
fun DayHighlight(date: LocalDate,day:String, startDate:String, completionString:String, habitId:Int,habitType:String,habitFreq:String,activeDays:MutableSet<String>){
    val formattedDate = FormatDate(date)

    var showDialog by remember { mutableStateOf(false) }
    val completionString = completionString.split(",")

    if ( calculateDateDifference(startDate,formattedDate) >0 && calculateDateDifference(startDate,formattedDate) <completionString.size && ((habitFreq.substring(0,4) =="Some" && day in activeDays)||habitFreq=="Daily"||habitFreq.substring(0,1)=="W")){
            val position = calculateDateDifference(startDate,formattedDate).toInt()
            val completed = completionString[position]
            if (completed=="0"){
                Text(formattedDate.substring(0,2), fontFamily = tilliumweb_bold_it, modifier = Modifier
                    .padding(12.dp)
                    .clickable(
                        onClick = { showDialog = true }))
            }
            else{
                Text(formattedDate.substring(0,2), fontFamily = tilliumweb_bold, modifier = Modifier
                    .padding(12.dp)
                    .clickable(
                        onClick = { showDialog = true }))
            }
        if (showDialog){
            if (habitType=="Bool"){
                HabitDayBoolDialog( onDismiss = {showDialog = false},habitId,position = position,completionString=completionString)
            }
            else{
                HabitDayNumDialog(onDismiss = {showDialog = false},habitId = habitId,position = position,completionString = completionString)
            }
        }
    }
    else{
        Text(formattedDate.substring(0,2), modifier = Modifier.padding(12.dp))
    }
}
