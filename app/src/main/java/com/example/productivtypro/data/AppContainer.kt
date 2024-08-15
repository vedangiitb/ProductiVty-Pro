package com.example.productivtypro.data

import android.content.Context
import com.example.productivtypro.data.Focus.FocusDataInst
import com.example.productivtypro.data.Focus.FocusRepo
import com.example.productivtypro.data.Habit.HabitDataInst
import com.example.productivtypro.data.Habit.HabitRepo
import com.example.productivtypro.data.RepeatTasks.RepeatTaskInst
import com.example.productivtypro.data.RepeatTasks.RepeatTasksRepo
import com.example.productivtypro.data.Tasks.TaksDataInst
import com.example.productivtypro.data.Tasks.TasksRepo

interface AppContainer {
    val taskRepo: TasksRepo

    val habitRepo: HabitRepo

    val focusRepo: FocusRepo

    val repeatTasksRepo : RepeatTasksRepo
}

class AppDataContainer(private val context: Context): AppContainer{
    override val taskRepo: TasksRepo by lazy {
        TasksRepo(TaksDataInst.getDatabase(context).itemDao())
    }

    override val habitRepo: HabitRepo by lazy {
        HabitRepo(HabitDataInst.getDatabase(context).HabitDao())
    }

    override val focusRepo: FocusRepo by lazy {
        FocusRepo(FocusDataInst.getDatabase(context).focusDao())
    }

    override val repeatTasksRepo: RepeatTasksRepo by lazy{
       RepeatTasksRepo(RepeatTaskInst.getDatabase(context).repeattaskDao())
    }

}