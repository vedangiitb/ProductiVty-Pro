package com.example.productivtypro.data.Habit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HabitData::class], version = 1, exportSchema = false)
abstract class HabitDataInst: RoomDatabase() {
    abstract fun HabitDao(): HabitDao
    companion object {
        @Volatile
        private var Instance: HabitDataInst? = null
        fun getDatabase(context: Context): HabitDataInst {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HabitDataInst::class.java, "habits_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}