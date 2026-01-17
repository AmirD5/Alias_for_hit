package com.hit.aliasgameapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hit.aliasgameapp.data.model.BoardSpace
import com.hit.aliasgameapp.data.model.Team
import com.hit.aliasgameapp.data.model.TeamPosition
import com.hit.aliasgameapp.repository.TeamRepository

class GameBoardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TeamRepository = TeamRepository(application)

    val allTeams: LiveData<List<Team>> = repository.allTeams

    private val _teamPositions = MutableLiveData<Map<Int, TeamPosition>>()
    val teamPositions: LiveData<Map<Int, TeamPosition>> = _teamPositions

    private val _boardSpaces = MutableLiveData<List<BoardSpace>>()
    val boardSpaces: LiveData<List<BoardSpace>> = _boardSpaces

    init {
        initializeBoardSpaces()
        observeTeams()
    }

    private fun initializeBoardSpaces() {
        val spaces = mutableListOf<BoardSpace>()
        for (i in 0 until 31) { // Changed from 30 to 31
            val number = if (i == 30) {
                0 // Special marker for "You Win!" space
            } else {
                (i % 8) + 1 // Numbers 1-8 cycling
            }
            spaces.add(BoardSpace(position = i, number = number))
        }
        _boardSpaces.value = spaces
    }

    private fun observeTeams() {
        allTeams.observeForever { teams ->
            updateTeamPositions(teams)
        }
    }

    private fun updateTeamPositions(teams: List<Team>) {
        val currentPositions = _teamPositions.value ?: emptyMap()
        val newPositions = mutableMapOf<Int, TeamPosition>()

        teams.forEach { team ->
            val existing = currentPositions[team.id]
            newPositions[team.id] = TeamPosition(
                teamId = team.id,
                teamName = team.name,
                teamColor = team.color,
                members = team.members,
                currentPosition = existing?.currentPosition ?: 0,
                currentPlayerIndex = existing?.currentPlayerIndex ?: 0
            )
        }

        _teamPositions.value = newPositions
    }

    fun moveTeam(teamId: Int, spaces: Int) {
        val positions = _teamPositions.value?.toMutableMap() ?: return
        val teamPosition = positions[teamId] ?: return

        val newPosition = (teamPosition.currentPosition + spaces).coerceAtMost(30) // Changed to 30 for "You Win!" position
        positions[teamId] = teamPosition.copy(currentPosition = newPosition)
        _teamPositions.value = positions
    }

    fun nextPlayer(teamId: Int) {
        val positions = _teamPositions.value?.toMutableMap() ?: return
        val teamPosition = positions[teamId] ?: return

        val membersList = teamPosition.members.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        if (membersList.isEmpty()) return

        val nextIndex = (teamPosition.currentPlayerIndex + 1) % membersList.size
        positions[teamId] = teamPosition.copy(currentPlayerIndex = nextIndex)
        _teamPositions.value = positions
    }

    fun getMembersList(teamPosition: TeamPosition): List<String> {
        return teamPosition.members.split(",").map { it.trim() }.filter { it.isNotEmpty() }
    }

    fun getCurrentPlayer(teamPosition: TeamPosition): String? {
        val members = getMembersList(teamPosition)
        return if (members.isNotEmpty() && teamPosition.currentPlayerIndex < members.size) {
            members[teamPosition.currentPlayerIndex]
        } else {
            null
        }
    }
}

