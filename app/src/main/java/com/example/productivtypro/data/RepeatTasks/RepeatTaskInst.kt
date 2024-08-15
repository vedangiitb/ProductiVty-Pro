package com.example.productivtypro.data.RepeatTasks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RepeatTasksData::class], version = 1, exportSchema = false)
abstract class RepeatTaskInst: RoomDatabase() {
    abstract fun repeattaskDao(): RepeatTasksDao
    companion object {
        @Volatile
        private var Instance: RepeatTaskInst? = null
        fun getDatabase(context: Context): RepeatTaskInst {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, RepeatTaskInst::class.java, "repeat_tasks_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}