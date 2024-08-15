package com.example.productivtypro.ViewModels.Habit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivtypro.data.Habit.HabitData
import com.example.productivtypro.data.Habit.HabitRepo
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class HabitEntryViewModel(private val habitRepo: HabitRepo):ViewModel() {
    private val _name = mutableStateOf("")

    private val _startdate = mutableStateOf("")

    private val _enddate = mutableStateOf("")

    private val _frequency = mutableStateOf("")

    private val _completionString = mutableStateOf("")

    private val _type = mutableStateOf("Bool")

    private val _hasReminder = mutableStateOf(false)

    private val _reminderTime = mutableStateOf("")

    fun setHabitName(name:String){
        _name.value = name
    }

    fun setStartDate(startdate: String){
        _startdate.value = startdate
    }

    fun setEndDate(enddate: String){
        _enddate.value = enddate
    }

    fun setFrequency(frequency:String){
        _frequency.value = frequency
    }

    fun upDateComplString(startDate: String, endDate: String,frequency: String){
        _completionString.value = List(getTotalDays(startDate, endDate, frequency)) { "0" }.joinTo(
            StringBuilder(),
            separator = ","
        ).toString()
    }

    fun updateType(type:String){
        _type.value = type
    }

    fun updateReminder(hasReminder:Boolean,reminderTime:String){
        _hasReminder.value = hasReminder
        _reminderTime.value = reminderTime

    }

    private fun getTotalDays(startDate: String, endDate: String,frequency: String): Int {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val startDateObj = dateFormat.parse(startDate)
        val endDateObj = dateFormat.parse(endDate)

        val diffInMillis = endDateObj.time - startDateObj.time
        val daysDiff = diffInMillis / (1000 * 60 * 60 * 24)

        return daysDiff.toInt() + 1
    }

    fun insertData(){
        if (_name.value.isNotEmpty()){
            viewModelScope.launch {
                habitRepo.insertItem(HabitData(name = _name.value,
                    startDate = _startdate.value,
                    endDate = _enddate.value,
                    frequency = _frequency.value,
                    completionString = _completionString.value,
                    type = _type.value)
                )
            }
        }
    }
}