package com.hit.aliasgameapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hit.aliasgameapp.data.model.Team
import com.hit.aliasgameapp.repository.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TeamRepository

    val allTeams: LiveData<List<Team>>

    init {
        repository = TeamRepository(application)
        allTeams = repository.allTeams
    }

    fun insert(team: Team) = viewModelScope.launch {
        repository.insert(team)
    }

    fun update(team: Team) = viewModelScope.launch {
        val oldTeam = repository.getTeamById(team.id)
        if (oldTeam != null && oldTeam.imagePath != team.imagePath) {
            deleteFile(oldTeam.imagePath)
        }
        repository.update(team)
    }

    fun delete(team: Team) = viewModelScope.launch {
        deleteFile(team.imagePath)
        repository.delete(team)
    }

    suspend fun getTeamById(id: Int): Team? {
        return repository.getTeamById(id)
    }

    private fun deleteFile(path: String?) {
        if (path != null) {
            val file = java.io.File(path)
            if (file.exists()) {
                file.delete()
            }
        }
    }
}