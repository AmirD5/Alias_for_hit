package com.hit.aliasgameapp.ui.gameboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.databinding.FragmentGameBoardBinding
import com.hit.aliasgameapp.viewmodel.GameBoardViewModel
import kotlin.random.Random

class GameBoardFragment : Fragment() {

    private var _binding: FragmentGameBoardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameBoardViewModel
    private var savedRandomNumbers: List<Int>? = null
    private var savedBoardPositions: List<Pair<Float, Float>>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameBoardViewModel::class.java]

        binding.cvBoard.layoutDirection = View.LAYOUT_DIRECTION_LTR

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.allTeams.observe(viewLifecycleOwner) { teams ->
            if (teams != null && teams.isNotEmpty()) {
                viewModel.initGame(teams)

                updatePawnsFromScore()
            }
        }

        viewModel.currentWord.observe(viewLifecycleOwner) { word ->
            binding.textViewWord.text = word
        }

        viewModel.timeLeft.observe(viewLifecycleOwner) { time ->
            binding.textViewTimer.text = time.toString()
        }

        viewModel.currentScore.observe(viewLifecycleOwner) { score ->
            binding.textViewScore.text = "Score: $score"
            updatePawnsFromScore()
        }

        viewModel.currentTeamName.observe(viewLifecycleOwner) { name ->
            binding.tvCurrentTurn.text = "Turn: $name"
            binding.tvOverlayTeamName.text = name
        }

        binding.buttonCorrect.setOnClickListener {
            viewModel.onCorrectAnswer()
        }

        binding.buttonSkip.setOnClickListener {
            viewModel.onSkipWord()
        }

        binding.btnStartRound.setOnClickListener {
            viewModel.startRound()
        }

        viewModel.isGameActive.observe(viewLifecycleOwner) { isActive ->
            if (isActive) {
                binding.gameOverlay.visibility = View.VISIBLE
                binding.btnStartRound.visibility = View.GONE
                binding.tvCurrentTurn.visibility = View.GONE
                binding.btnBack.visibility = View.GONE
            } else {
                binding.gameOverlay.visibility = View.GONE
                binding.btnStartRound.visibility = View.VISIBLE
                binding.tvCurrentTurn.visibility = View.VISIBLE
                binding.btnBack.visibility = View.VISIBLE
            }
        }

        if (!viewModel.isBoardAlreadyGenerated) {
            savedRandomNumbers = generateRandomNumbers()
            savedBoardPositions = calculatePositions()

            drawBoard()
            updatePawnsFromScore()

            viewModel.isBoardAlreadyGenerated = true
        } else {
            drawBoard()
            updatePawnsFromScore()
        }
    }



    private fun updatePawnsFromScore() {
        val teams = viewModel.allTeams.value
        if (teams.isNullOrEmpty()) return

        val positionsMap = mutableMapOf<Int, com.hit.aliasgameapp.data.model.TeamPosition>()
        val totalBoardSpaces = 23

        teams.forEachIndexed { index, team ->
            val currentScore = viewModel.getTeamTotalScore(index)

            if (currentScore >= totalBoardSpaces - 1) {
                showWinDialog(team.name, currentScore)
                return
            }

            val positionData = com.hit.aliasgameapp.data.model.TeamPosition(
                teamId = team.id,
                teamName = team.name,
                teamColor = team.color,
                currentPosition = currentScore,
                members = team.members ?: "",
                currentPlayerIndex = 0
            )
            positionsMap[team.id] = positionData
        }

        drawPawns(positionsMap)
    }


    private fun drawBoard() {
        binding.boardContainer.removeAllViews()

        // Generate random numbers for positions (1-8)
        val randomNumbers = generateRandomNumbers()

        // Calculate positions - just first row for now
        val positions = calculatePositions()

        savedRandomNumbers = randomNumbers
        savedBoardPositions = positions
        // Draw board spaces (no connecting lines for now)
        positions.forEachIndexed { index, (x, y) ->
            val circle = createBoardSpace(index, randomNumbers[index])
            circle.x = x
            circle.y = y
            binding.boardContainer.addView(circle)
        }
    }

    private fun generateRandomNumbers(): List<Int> {
        // Position 0 (Start) and 21 (You Win) have no numbers
        // Positions 5, 12, 18 always have number 5
        // Other positions get random numbers 1-8
        return List(22) { index ->
            when (index) {
                0 -> 0  // No number - Start position
                5, 12, 18 -> 5  // Special positions always have 5
                21 -> 0  // No number - You Win position
                else -> Random.nextInt(1, 9)  // Random 1-8
            }
        }
    }

    private fun calculatePositions(): List<Pair<Float, Float>> {
        val positions = mutableListOf<Pair<Float, Float>>()

        // Fixed spacing of 220f between positions
        val spacing = 150f
        val startX = 30f  // Top-left corner
        val startY = 30f  // Top-left corner

        // Pattern: Start, down 1-6, right 7-8, up 9-12, right 13-14, down 15-18, right to You Win (19)

        // Position 0: Start
        positions.add(Pair(startX, startY))

        // Positions 1-6: Going down from Start
        for (i in 1..6) {
            positions.add(Pair(startX, startY + i * spacing))
        }

        // Positions 7-8: Going right from position 6
        positions.add(Pair(startX + spacing, startY + 6 * spacing))  // 7
        positions.add(Pair(startX + 2 * spacing, startY + 6 * spacing))  // 8

        // Positions 9-12: Going up from position 8
        for (i in 5 downTo 2) {
            positions.add(Pair(startX + 2 * spacing, startY + i * spacing))  // 9, 10, 11, 12
        }

        // Positions 13-14: Going right from position 12
        positions.add(Pair(startX + 3 * spacing, startY + 2 * spacing))  // 13
        positions.add(Pair(startX + 4 * spacing, startY + 2 * spacing))  // 14

        // Positions 15-20: Going down from position 14 (extended to 6 positions)
        for (i in 3..8) {
            positions.add(Pair(startX + 4 * spacing, startY + i * spacing))  // 15, 16, 17, 18, 19, 20
        }

        // Position 21: You Win! - Going right from position 20
        positions.add(Pair(startX + 5 * spacing, startY + 8 * spacing))  // 21 - You Win!

        return positions
    }

    private fun createBoardSpace(position: Int, number: Int): View {
        val view = when (position) {
            0 -> {
                // Start position
                layoutInflater.inflate(R.layout.item_board_space, binding.boardContainer, false).apply {
                    findViewById<TextView>(R.id.tvCardNumber)?.apply {
                        text = getString(R.string.start)
                        textSize = 14f
                    }
                }
            }
            21 -> {
                // You Win position
                layoutInflater.inflate(R.layout.item_board_space_win, binding.boardContainer, false)
            }
            else -> {
                // Regular positions with numbers
                val layoutRes = if (number == 5) {
                    R.layout.item_board_space_special
                } else {
                    R.layout.item_board_space
                }

                layoutInflater.inflate(layoutRes, binding.boardContainer, false).apply {
                    findViewById<TextView>(R.id.tvCardNumber)?.text = number.toString()
                }
            }
        }

        return view
    }

    private fun drawPawns(positions: Map<Int, com.hit.aliasgameapp.data.model.TeamPosition>) {
        binding.pawnsContainer.removeAllViews()

        val boardPositions = calculatePositions()
        val grouped = positions.values.groupBy { it.currentPosition }

        grouped.forEach { (position, teams) ->
            if (position < boardPositions.size) {
                val (baseX, baseY) = boardPositions[position]

                teams.forEachIndexed { index, team ->
                    val pawn = createPawn(team, position)

                    // Better separation for pawns on same position
                    val offsetX = (index % 2) * 30f
                    val offsetY = (index / 2) * 30f

                    pawn.x = baseX + 15f + offsetX
                    pawn.y = baseY + 15f + offsetY

                    binding.pawnsContainer.addView(pawn)
                }
            }
        }
    }

    private fun createPawn(team: com.hit.aliasgameapp.data.model.TeamPosition, position: Int): View {
        val pawn = layoutInflater.inflate(R.layout.item_team_pawn, binding.pawnsContainer, false)

        val icon = pawn.findViewById<ImageView>(R.id.ivPawn)
        icon.setColorFilter(ContextCompat.getColor(requireContext(), getTeamColor(team.teamColor)))

        // Add click listener to show tooltip with position
        pawn.setOnClickListener {
            showTeamTooltip(team, position)
        }

        return pawn
    }

    private fun showTeamTooltip(team: com.hit.aliasgameapp.data.model.TeamPosition, position: Int) {
        // Split members string by comma
        val membersList = team.members.split(",").map { it.trim() }.filter { it.isNotBlank() }

        if (membersList.isEmpty()) {
            AlertDialog.Builder(requireContext())
                .setTitle(team.teamName)
                .setMessage(getString(R.string.no_members_listed))
                .setPositiveButton(getString(R.string.close), null)
                .create()
                .show()
            return
        }

        // Determine current reader based on currentPlayerIndex
        val currentReaderIndex = team.currentPlayerIndex % membersList.size

        // Get the random numbers to display the card number at this position
        val randomNumbers = generateRandomNumbers()
        val cardNumber = if (position < randomNumbers.size) randomNumbers[position] else 0

        // Build message with position, card number, and highlighted reader
        val message = buildString {
            // Show position and card number
            append("Position: $position")
            if (cardNumber > 0) {
                append("\nCard Number: $cardNumber")
            }
            append("\n\n")
            append(getString(R.string.team_members))
            append("\n\n")
            membersList.forEachIndexed { index, member ->
                if (index == currentReaderIndex) {
                    append("▶ ") // Arrow to indicate current reader
                    append(member)
                    append(" ")
                    append(getString(R.string.current_reader))
                    append(" ◀")
                } else {
                    append(member)
                }
                append("\n")
            }
        }

        AlertDialog.Builder(requireContext())
            .setTitle(team.teamName)
            .setMessage(message)
            .setPositiveButton(getString(R.string.close), null)
            .create()
            .show()
    }

    private fun getTeamColor(colorName: String): Int {
        return when (colorName) {
            "Red", "אדום" -> R.color.team_red
            "Blue", "כחול" -> R.color.team_blue
            "Green", "ירוק" -> R.color.team_green
            "Yellow", "צהוב" -> R.color.team_yellow
            "Orange", "כתום" -> R.color.team_orange
            "Purple", "סגול" -> R.color.team_purple
            "Pink", "ורוד" -> R.color.team_pink
            "Teal", "טורקיז" -> R.color.team_teal
            else -> R.color.black
        }
    }
    private fun navigateToResult(winnerName: String, score: Int) {
        val bundle = Bundle().apply {
            putString("winnerName", winnerName)
            putInt("score", score)
        }

        findNavController().navigate(R.id.action_gameBoardFragment_to_resultFragment, bundle)
    }

    private fun showWinDialog(winnerName: String, score: Int) {
        if (viewModel.isGameFinished) return
        viewModel.isGameFinished = true

        viewModel.endGame()

        AlertDialog.Builder(requireContext())
            .setTitle("🎉 WE HAVE A WINNER! 🎉")
            .setMessage("Team $winnerName reached the finish line!")
            .setCancelable(false)
            .setPositiveButton("See Results") { _, _ ->
                navigateToResult(winnerName, score)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
