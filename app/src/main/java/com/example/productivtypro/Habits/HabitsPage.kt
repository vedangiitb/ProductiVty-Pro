package com.example.productivtypro.Habits

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productivtypro.HomePage.HomePage
import com.example.productivtypro.ViewModels.AppViewModelProvider
import com.example.productivtypro.ViewModels.Habit.HabitDisplayViewModel

@Composable
fun HabitPage(navController: NavController,habitDisplayViewModel: HabitDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    val habitDispUiState by habitDisplayViewModel.habitDispUiState.collectAsState()

    val habitList = habitDispUiState.habitsList
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {navController.navigate(HomePage.addHabitPage.name)}) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add new Habit") }
    }) {
        Column(modifier = Modifier.padding(it)) {
            DisplayHabits(habitList = habitList)
        }
    }
}