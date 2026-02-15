package com.hit.aliasgameapp.di

import android.content.Context
import androidx.room.Room
import com.hit.aliasgameapp.data.dao.TeamDao
import com.hit.aliasgameapp.data.dao.WordDao
import com.hit.aliasgameapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // 1. Teach Hilt how to create the Database
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "team_database" // Must match the name in your existing code!
        )
            .fallbackToDestructiveMigration() // Matches your .fallbackToDestructiveMigration(true)
            .build()
    }

    // 2. Teach Hilt how to give you the WordDao
    @Provides
    fun provideWordDao(database: AppDatabase): WordDao {
        return database.wordDao()
    }

    // 3. Teach Hilt how to give your TEAM the TeamDao (Bonus points for being a good teammate!)
    @Provides
    fun provideTeamDao(database: AppDatabase): TeamDao {
        return database.teamDao()
    }
}