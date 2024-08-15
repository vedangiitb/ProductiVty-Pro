package com.example.productivtypro.data.Focus

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FocusData::class], version = 1, exportSchema = false)
abstract class FocusDataInst: RoomDatabase() {
    abstract fun focusDao(): FocusDao
    companion object {
        @Volatile
        private var Instance: FocusDataInst? = null
        fun getDatabase(context: Context): FocusDataInst {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FocusDataInst::class.java, "focus_session_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}