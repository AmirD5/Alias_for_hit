package com.hit.aliasgameapp.viewmodel

import android.app.Application
import android.os.CountDownTimer
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

    private val _currentWord = MutableLiveData<String>()
    val currentWord: LiveData<String> = _currentWord

    private val _timeLeft = MutableLiveData<Int>()
    val timeLeft: LiveData<Int> = _timeLeft

    private val _currentScore = MutableLiveData<Int>()
    val currentScore: LiveData<Int> = _currentScore

    private val _currentTeamName = MutableLiveData<String>()
    val currentTeamName: LiveData<String> = _currentTeamName

    private val _isGameActive = MutableLiveData<Boolean>(false)
    val isGameActive: LiveData<Boolean> = _isGameActive

    private var timer: CountDownTimer? = null
    private var teamsList: List<Team> = emptyList()
    private var currentTeamIndex = 0
    private var roundScore = 0
    var isBoardAlreadyGenerated = false

    private val teamTotalScores = mutableMapOf<Int, Int>()

    var isGameFinished = false

    fun endGame() {
        _isGameActive.value = false
        timer?.cancel()
    }

    private val wordList = listOf(
        "גלידה", "שמש", "כדורגל", "מחשב", "אוניברסיטה", "ים", "פיצה",
        "טלפון", "כלב", "חתול", "מטוס", "ישראל", "גיטרה", "חופש"
    ).shuffled()

    private var wordIndex = 0

    fun getCurrentTeamIndex(): Int {
        return currentTeamIndex
    }

    fun getTeamTotalScore(index: Int): Int {
        return teamTotalScores[index] ?: 0
    }

    init {
        initializeBoardSpaces()
        observeTeams()
    }

    private fun initializeBoardSpaces() {
        val spaces = mutableListOf<BoardSpace>()
        for (i in 0 until 30) { // Original: 30 spaces only
            val number = (i % 8) + 1 // Numbers 1-8 cycling
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

        val newPosition = (teamPosition.currentPosition + spaces).coerceAtMost(29) // Max position 29
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

    fun startGame(teams: List<Team>) {
        if (teams.isEmpty()) return
        teamsList = teams
        currentTeamIndex = 0
        startRound()
    }

    fun startRound() {
        val currentTeam = teamsList[currentTeamIndex]
        _currentTeamName.value = currentTeam.name
        roundScore = 0
        _currentScore.value = roundScore
        _isGameActive.value = true

        nextWord()
        startTimer()
    }

    private fun nextWord() {
        if (wordIndex >= wordList.size) {
            wordIndex = 0
        }
        _currentWord.value = wordList[wordIndex]
        wordIndex++
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                _timeLeft.value = 0
                _isGameActive.value = false
                advanceTurn()
            }
        }.start()
    }

    fun onCorrectAnswer() {
        roundScore++
        _currentScore.value = roundScore
        val currentTotal = (teamTotalScores[currentTeamIndex] ?: 0) + 1
        teamTotalScores[currentTeamIndex] = currentTotal

        nextWord()
    }

    fun onSkipWord() {
        roundScore = (roundScore - 1).coerceAtLeast(0)
        _currentScore.value = roundScore
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    fun initGame(teams: List<Team>) {
        if (teamsList.isNotEmpty()) return

        teamsList = teams
        currentTeamIndex = 0
        _currentTeamName.value = teamsList[currentTeamIndex].name
    }

    fun advanceTurn() {
        if (teamsList.isNotEmpty()) {
            currentTeamIndex = (currentTeamIndex + 1) % teamsList.size
            _currentTeamName.value = teamsList[currentTeamIndex].name
        }
    }


    fun endRound() {
        _isGameActive.value = false
        timer?.cancel()

        val currentTotal = teamTotalScores[currentTeamIndex] ?: 0
        teamTotalScores[currentTeamIndex] = currentTotal + roundScore

        advanceTurn()
    }
}

