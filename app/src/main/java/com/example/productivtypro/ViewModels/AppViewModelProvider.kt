package com.example.productivtypro.ViewModels


import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.productivtypro.ProductivtyProApplication
import com.example.productivtypro.ViewModels.Focus.FocusViewModel
import com.example.productivtypro.ViewModels.Habit.HabitDisplayViewModel
import com.example.productivtypro.ViewModels.Habit.HabitEditViewModel
import com.example.productivtypro.ViewModels.Habit.HabitEntryViewModel
import com.example.productivtypro.ViewModels.RepeatTask.RepeatTaskViewModel
import com.example.productivtypro.ViewModels.Task.TaskDisplayViewModel
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.ViewModels.Task.TaskEntryViewModel

object AppViewModelProvider{
    val Factory = viewModelFactory {
        initializer {
            TaskEntryViewModel(taskApplication().container.taskRepo)
        }
        initializer {
            TaskDisplayViewModel(taskApplication().container.taskRepo)
        }
        initializer {
            TaskEditViewModel(taskApplication().container.taskRepo)
        }
        initializer {
            HabitEntryViewModel(habitApplication().container.habitRepo)
        }
        initializer {
            HabitDisplayViewModel(habitApplication().container.habitRepo)
        }
        initializer {
            HabitEditViewModel(habitApplication().container.habitRepo)
        }
        initializer {
            FocusViewModel(focusApplication().container.focusRepo)
        }
        initializer {
            RepeatTaskViewModel(repeatTaskApplication().container.repeatTasksRepo)
        }
    }
}

fun CreationExtras.taskApplication(): ProductivtyProApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ProductivtyProApplication)

fun CreationExtras.habitApplication(): ProductivtyProApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ProductivtyProApplication)

fun CreationExtras.focusApplication(): ProductivtyProApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ProductivtyProApplication)

fun CreationExtras.repeatTaskApplication():ProductivtyProApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ProductivtyProApplication)