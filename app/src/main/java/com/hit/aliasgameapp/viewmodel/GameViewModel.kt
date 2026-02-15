package com.hit.aliasgameapp.ui.cards

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hit.aliasgameapp.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    // 1. Word State
    private val _words = MutableStateFlow<List<String>>(emptyList())
    val words: StateFlow<List<String>> = _words.asStateFlow()

    // 2. Score State
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    // 3. Timer State
    private val _timerText = MutableStateFlow("Loading...") // Start with "Loading"
    val timerText: StateFlow<String> = _timerText.asStateFlow()

    // 4. Game Over Event
    private val _isGameOver = MutableStateFlow(false)
    val isGameOver: StateFlow<Boolean> = _isGameOver.asStateFlow()

    // --- NEW: Buffer Variables ---
    private var cachedNextCard: List<String>? = null
    private var isFetching = false

    private var timer: CountDownTimer? = null

    init {
        // Start the game loop
        loadInitialGame()
    }

    // Step 1: Load the very first card + Start Timer
    private fun loadInitialGame() {
        if (isFetching) return
        isFetching = true

        viewModelScope.launch {
            val result = repository.getCardWords()

            result.onSuccess { firstCard ->
                _words.value = firstCard
                isFetching = false

                // Only start timer after first card is ready!
                startTimer()

                // Immediately go fetch the NEXT card
                preloadNextCard()
            }.onFailure {
                handleError()
            }
        }
    }

    // Step 2: Fetch the next card silently in the background
    private fun preloadNextCard() {
        if (isFetching) return
        isFetching = true

        viewModelScope.launch {
            val result = repository.getCardWords()
            result.onSuccess { nextCard ->
                cachedNextCard = nextCard
                isFetching = false
            }.onFailure {
                // If we can't prefetch (e.g., deck empty), we'll handle it when user clicks next
                isFetching = false
            }
        }
    }

    // Step 3: Instant Swap when button is clicked
    private fun swapCards() {
        if (cachedNextCard != null) {
            // INSTANTLY show the buffered card
            _words.value = cachedNextCard!!
            cachedNextCard = null

            // Go get the next one
            preloadNextCard()
        } else {
            // Emergency: If user clicked faster than DB, force a load
            loadInitialGame()
        }
    }

    private fun startTimer() {
        if (timer != null) return // Prevent double timers

        _timerText.value = "60"
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                _timerText.value = secondsRemaining.toString()
            }

            override fun onFinish() {
                _timerText.value = "0"
                _isGameOver.value = true
                viewModelScope.launch { repository.endCurrentGame() }
            }
        }.start()
    }

    fun onCorrect() {
        if (_isGameOver.value) return
        _score.value += 1
        swapCards() // <--- Instant swap
    }

    fun onIncorrect() {
        if (_isGameOver.value) return
        if (_score.value > 0) {
            _score.value -= 1
        }
        swapCards() // <--- Instant swap
    }

    private fun handleError() {
        _timerText.value = "Error!"
        _isGameOver.value = true
        timer?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
        viewModelScope.launch { repository.endCurrentGame() }
    }
}