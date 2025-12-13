package com.hit.aliasgameapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hit.aliasgameapp.data.database.AppDatabase
import com.hit.aliasgameapp.data.model.Team
import com.hit.aliasgameapp.repository.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TeamRepository

    val allTeams: LiveData<List<Team>>

    init {
        val dao = AppDatabase.getDatabase(application).teamDao()
        repository = TeamRepository(dao)
        allTeams = repository.allTeams
    }

    fun insert(team: Team) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(team)
    }

    fun update(team: Team) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(team)
    }

    fun delete(team: Team) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(team)
    }

    fun getTeamById(id: Int): Team? {
        return repository.getTeamById(id)
    }
}