package com.example.productivtypro.Task

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.ViewModels.Others.DateViewModel
import com.example.productivtypro.ui.theme.md_theme_dark_primaryContainer
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig


@Composable
fun Planned(viewModel: DateViewModel = viewModel()){
    Column {
        Kalendar(currentDay = null, kalendarType = KalendarType.Oceanic, onDayClick = {date, t->
            viewModel.chageDate(date,t)},kalendarDayKonfig = KalendarDayKonfig(textSize = 15.sp, size = 50.dp, selectedTextColor = Color.White, textColor = Color.White),
            kalendarColors = KalendarColors(List(12){ KalendarColor(backgroundColor = Color.Transparent, dayBackgroundColor = Color.DarkGray, headerTextColor = md_theme_dark_primaryContainer ) })
        )
        val day: Int = viewModel.day.value
        val month: Int = viewModel.month.value
        val year: Int = viewModel.year.value
        val dateString = "$day-$month-$year"

        Column {
            AddNewTask(dateString)
            DisplayDaysTasks(dateString = dateString)
        }
    }

}


