package com.example.productivtypro.ViewModels.Others

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.himanshoe.kalendar.KalendarEvent
import kotlinx.datetime.LocalDate
import java.util.Calendar

class DateViewModel: ViewModel(){
    val calendar = Calendar.getInstance()

    private val _day = mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH))
    val day : State<Int> = _day

    private val _month = mutableStateOf(calendar.get(Calendar.MONTH)+1)
    val month: State<Int> = _month

    private val _year = mutableStateOf(calendar.get(Calendar.YEAR))
    val year: State<Int> = _year

    fun getTodayDate():String{
        return "${day.value}-${month.value}-${year.value}"
    }

    fun getTomorrowDate():String{
        var tomorrow = Calendar.getInstance()
        tomorrow.add(Calendar.DAY_OF_MONTH, 1)
        return "${tomorrow.get(Calendar.DAY_OF_MONTH)}-${tomorrow.get(Calendar.MONTH)+1}-${tomorrow.get(Calendar.YEAR)}"
    }

    fun chageDate(date: LocalDate, num:List<KalendarEvent>){
        _year.value = date.year
        _month.value = date.monthNumber
        _day.value = date.dayOfMonth
    }
}