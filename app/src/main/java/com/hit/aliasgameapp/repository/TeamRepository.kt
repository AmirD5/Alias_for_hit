package com.hit.aliasgameapp.repository

import androidx.lifecycle.LiveData
import com.hit.aliasgameapp.data.dao.TeamDao
import com.hit.aliasgameapp.data.model.Team

class TeamRepository(private val dao: TeamDao) {

    val allTeams: LiveData<List<Team>> = dao.getAllTeams()

    suspend fun insert(team: Team) = dao.insert(team)

    suspend fun update(team: Team) = dao.update(team)

    suspend fun delete(team: Team) = dao.delete(team)

    fun getTeamById(id: Int): Team? = dao.getTeamById(id)
}