package com.hit.aliasgameapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hit.aliasgameapp.data.dao.TeamDao
import com.hit.aliasgameapp.data.model.Team
import com.hit.aliasgameapp.data.dao.WordDao
import com.hit.aliasgameapp.data.model.WordEntity

@Database(entities = [WordEntity::class,Team::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun wordDao(): WordDao

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
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}