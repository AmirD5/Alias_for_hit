package com.hit.aliasgameapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hit.aliasgameapp.data.dao.TeamDao
import com.hit.aliasgameapp.data.model.Team

@Database(entities = [Team::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "team_database"
                )
                    .fallbackToDestructiveMigration(false)  // This deletes old data and recreates the table
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}