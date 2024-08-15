package com.example.productivtypro.ViewModels.Habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivtypro.data.Habit.HabitData
import com.example.productivtypro.data.Habit.HabitRepo
import kotlinx.coroutines.launch

class HabitEditViewModel(private val habitRepo: HabitRepo): ViewModel()  {
    fun UpdateCompletionString(habitId:Int,newString: String){
        viewModelScope.launch {
            habitRepo.updateCompletionString(habitId,newString)
        }
    }

    fun deleteHabit(habitData: HabitData){
        viewModelScope.launch {
            habitRepo.deleteItem(habitData)
        }
    }

    fun updateName(habitId: Int,newString: String){
        viewModelScope.launch {
            habitRepo.updateName(habitId,newString)
        }
    }

    fun updateEndDate(habitId: Int,newDate:String){
        viewModelScope.launch {
            habitRepo.updateEndDate(habitId,newDate)
        }
    }


    fun updateComplString(habitId: Int,difference:Long,complString:String){
        var newComplString = complString
        if (difference>0){
            newComplString += ",0".repeat(difference.toInt())
        }
        else{
            newComplString = complString.substring(0,((complString.length-1)/2 + 1 +  difference.toInt())*2-1)
        }

        viewModelScope.launch {
            habitRepo.updateComplString(habitId,newComplString)
        }

    }
}