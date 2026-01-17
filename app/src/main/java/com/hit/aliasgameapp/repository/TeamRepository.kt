package com.hit.aliasgameapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.hit.aliasgameapp.data.dao.TeamDao
import com.hit.aliasgameapp.data.database.AppDatabase
import com.hit.aliasgameapp.data.model.Team

class TeamRepository(context: Context) {

    private val dao: TeamDao

    init {
        val database = AppDatabase.getDatabase(context)
        dao = database.teamDao()
    }

    val allTeams: LiveData<List<Team>> = dao.getAllTeams()

    suspend fun insert(team: Team) = dao.insert(team)

    suspend fun update(team: Team) = dao.update(team)

    suspend fun delete(team: Team) = dao.delete(team)

    suspend fun getTeamById(id: Int) = dao.getTeamById(id)
}