package com.example.productivtypro.Habits.DialogBoxes

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Habit.HabitEditViewModel

@Composable
fun HabitDayBoolDialog(onDismiss :()->Unit,
                   habitId: Int,
                   habitEditViewModel: HabitEditViewModel = viewModel(factory = AppViewModelProvider.Factory),
                   position:Int,
                   completionString: List<String>){
    Dialog(onDismissRequest = {onDismiss()},
        properties = DialogProperties(dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false)
    ){
        var newString: List<String>
        var newVal = "1"

        if (completionString[position]=="1"){
            newVal = "0"
        }
        Button(onClick = {
            newString = completionString.subList(0,position) + newVal + completionString.subList(minOf(position+1,completionString.size),completionString.size)

            habitEditViewModel.UpdateCompletionString(habitId,newString.joinToString(separator = ","))
            })
            {
            if (completionString[position]=="0"){
                Text("Mark As Done")
            }
            else{
                Text("Mark As UnDone")
            }
        }
    }
}


@Composable
fun HabitDayNumDialog(onDismiss :()->Unit,
                      habitId: Int,
                      habitEditViewModel: HabitEditViewModel = viewModel(factory = AppViewModelProvider.Factory),
                      position:Int,
                      completionString: List<String>){
    Dialog(onDismissRequest = {onDismiss()}) {
        Row {
            IconButton(onClick = { if (completionString[position]!="0"){
                val newVal = completionString[position].toInt()-1
                val newString = completionString.subList(0,position) + newVal.toString() + completionString.subList(minOf(position+1,completionString.size),completionString.size)
                habitEditViewModel.UpdateCompletionString(habitId,newString.joinToString(separator = ","))
            }
            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Add")
            }
            Text(completionString[position])

            IconButton(onClick = { val newVal = completionString[position].toInt()+1
                val newString = completionString.subList(0,position) + newVal.toString() + completionString.subList(minOf(position+1,completionString.size),completionString.size)
                habitEditViewModel.UpdateCompletionString(habitId,newString.joinToString(separator = ","))}) {
                Icon(imageVector = Icons.Default.Add,contentDescription = null)
            }

        }
    }
}