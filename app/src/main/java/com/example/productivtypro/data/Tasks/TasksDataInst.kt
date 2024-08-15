package com.example.productivtypro.data.Tasks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TasksData::class], version = 1, exportSchema = false)
abstract class TaksDataInst: RoomDatabase() {
    abstract fun itemDao(): TasksDao
    companion object {
        @Volatile
        private var Instance: TaksDataInst? = null
        fun getDatabase(context: Context): TaksDataInst {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TaksDataInst::class.java, "tasks_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}