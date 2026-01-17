package com.hit.aliasgameapp.ui.gameboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.databinding.FragmentGameBoardBinding
import com.hit.aliasgameapp.viewmodel.GameBoardViewModel

class GameBoardFragment : Fragment() {

    private var _binding: FragmentGameBoardBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameBoardViewModel

    private val spaceSize = 91  // Increased by 30% (was 70dp)
    private val spaceMargin = 5 // Margin around circle in dp
    private val totalSpaceSize = spaceSize + (spaceMargin * 2) // Total space with margin = 101dp

    private var scaleFactor = 0.4f // Start zoomed out to see more of board
    private val minScale = 0.3f // Zoom out even more to see whole board
    private val maxScale = 3f   // Zoom in to see one space clearly
    private val zoomStep = 0.2f // Amount to zoom per button click

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

        setupZoom()
        setupObservers()
    }

    private fun setupZoom() {
        // Set initial zoom
        applyZoom()

        // Zoom in button
        binding.btnZoomIn.setOnClickListener {
            scaleFactor = (scaleFactor + zoomStep).coerceAtMost(maxScale)
            applyZoom()
        }

        // Zoom out button
        binding.btnZoomOut.setOnClickListener {
            scaleFactor = (scaleFactor - zoomStep).coerceAtLeast(minScale)
            applyZoom()
        }
    }

    private fun applyZoom() {
        binding.boardScrollContainer.scaleX = scaleFactor
        binding.boardScrollContainer.scaleY = scaleFactor
    }

    private fun setupObservers() {
        viewModel.boardSpaces.observe(viewLifecycleOwner) { spaces ->
            if (spaces != null) {
                updateBoardSpaces(spaces)
            }
        }

        viewModel.teamPositions.observe(viewLifecycleOwner) { positions ->
            if (positions != null) {
                updateTeamPawns(positions)
            }
        }
    }

    private fun updateBoardSpaces(spaces: List<com.hit.aliasgameapp.data.model.BoardSpace>) {
        binding.boardContainer.removeAllViews()

        val spiralPositions = calculateSpiralPositions()

        // Add single connecting dot between each position FIRST (behind circles)
        addConnectingDots(spiralPositions)

        // Add spaces to board on top
        spaces.forEachIndexed { index, space ->
            if (index < spiralPositions.size) {
                val spaceView = createBoardSpaceView(space)
                val (x, y) = spiralPositions[index]

                val layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                spaceView.layoutParams = layoutParams
                spaceView.x = x
                spaceView.y = y

                binding.boardContainer.addView(spaceView)
            }
        }
    }

    private fun addConnectingDots(positions: List<Pair<Float, Float>>) {
        for (i in 0 until positions.size - 1) {
            val (x1, y1) = positions[i]
            val (x2, y2) = positions[i + 1]

            // Calculate center of each space (total space is 80dp, so center is at 40dp)
            val centerX1 = x1 + dpToPx(totalSpaceSize / 2)
            val centerY1 = y1 + dpToPx(totalSpaceSize / 2)
            val centerX2 = x2 + dpToPx(totalSpaceSize / 2)
            val centerY2 = y2 + dpToPx(totalSpaceSize / 2)

            // Add ONE dot in the middle between the two positions
            val dotX = (centerX1 + centerX2) / 2
            val dotY = (centerY1 + centerY2) / 2

            val dot = createConnectingDot()
            val layoutParams = RelativeLayout.LayoutParams(
                dpToPx(6),
                dpToPx(6)
            )
            dot.layoutParams = layoutParams
            dot.x = dotX - dpToPx(3) // Center the dot
            dot.y = dotY - dpToPx(3)

            binding.boardContainer.addView(dot)
        }
    }

    private fun createConnectingDot(): View {
        val dot = View(requireContext())
        dot.setBackgroundResource(R.drawable.connecting_dot)
        return dot
    }

    private fun createBoardSpaceView(space: com.hit.aliasgameapp.data.model.BoardSpace): View {
        // Use special layout for position 30 (You Win!)
        val layoutId = if (space.position == 30) {
            R.layout.item_board_space_win
        } else if (space.number == 5) {
            // Use special glowing layout for number 5 (special feature)
            R.layout.item_board_space_special
        } else {
            R.layout.item_board_space
        }

        val spaceView = layoutInflater.inflate(layoutId, binding.boardContainer, false)

        // Only set number for regular spaces (not the win space)
        if (space.position != 30) {
            val tvNumber = spaceView.findViewById<android.widget.TextView>(R.id.tvCardNumber)
            tvNumber?.text = space.number.toString()
        }

        return spaceView
    }

    private fun updateTeamPawns(positions: Map<Int, com.hit.aliasgameapp.data.model.TeamPosition>) {
        // Remove existing pawns
        binding.pawnsContainer.removeAllViews()

        val spiralPositions = calculateSpiralPositions()

        // Group teams by position to handle overlapping
        val teamsByPosition = positions.values.groupBy { it.currentPosition }

        teamsByPosition.forEach { (position, teams) ->
            if (position < spiralPositions.size) {
                teams.forEachIndexed { index, teamPosition ->
                    val pawnView = createPawnView(teamPosition, spiralPositions, index, teams.size)
                    binding.pawnsContainer.addView(pawnView)
                }
            }
        }
    }

    private fun createPawnView(
        teamPosition: com.hit.aliasgameapp.data.model.TeamPosition,
        spiralPositions: List<Pair<Float, Float>>,
        index: Int,
        totalAtPosition: Int
    ): View {
        val pawnView = layoutInflater.inflate(R.layout.item_team_pawn, binding.pawnsContainer, false)

        val pawnIcon = pawnView.findViewById<android.widget.ImageView>(R.id.ivPawn)
        val tvTeamName = pawnView.findViewById<android.widget.TextView>(R.id.tvPawnTeamName)

        // Set pawn color
        val colorResId = getColorResourceId(teamPosition.teamColor)
        pawnIcon.setColorFilter(ContextCompat.getColor(requireContext(), colorResId))

        tvTeamName.text = teamPosition.teamName
        tvTeamName.visibility = View.GONE // Hide name to reduce clutter on board

        // Position pawn on board - center it on the space
        val (x, y) = spiralPositions[teamPosition.currentPosition]
        val layoutParams = RelativeLayout.LayoutParams(
            dpToPx(25), // Pawn size
            dpToPx(25)
        )
        pawnView.layoutParams = layoutParams

        // Calculate base center offset
        val baseOffsetX = dpToPx((totalSpaceSize - 25) / 2)
        val baseOffsetY = dpToPx((totalSpaceSize - 25) / 2)

        // If multiple pawns at same position, offset them in a circle pattern
        val angleOffset = (index * 360f / totalAtPosition) * Math.PI / 180
        val radiusOffset = if (totalAtPosition > 1) dpToPx(15) else 0

        val offsetX = (radiusOffset * Math.cos(angleOffset)).toFloat()
        val offsetY = (radiusOffset * Math.sin(angleOffset)).toFloat()

        pawnView.x = x + baseOffsetX + offsetX
        pawnView.y = y + baseOffsetY + offsetY

        // Add elevation to ensure pawn is visible on top
        pawnView.elevation = dpToPx(8).toFloat()

        // Set click listener to show tooltip
        pawnView.setOnClickListener {
            showTeamTooltip(teamPosition)
        }

        return pawnView
    }

    private fun calculateSpiralPositions(): List<Pair<Float, Float>> {
        val positions = mutableListOf<Pair<Float, Float>>()
        val spaceSizePx = dpToPx(totalSpaceSize).toFloat() // Use 101dp (91 + 10 margin)
        val margin = dpToPx(10).toFloat() // Reduced from 15dp to 10dp for tighter spacing

        // Start from outer edge and spiral inward (square pattern)
        var x = 80f // Start further from edge to prevent cutting
        var y = 80f
        var direction = 0 // 0=right, 1=down, 2=left, 3=up
        var steps = 6 // Reduced from 7 to 6 to make spiral tighter
        var stepsTaken = 0
        var directionChanges = 0

        for (i in 0 until 31) { // Changed from 30 to 31 positions
            positions.add(Pair(x, y))

            // Move in current direction
            when (direction) {
                0 -> x += spaceSizePx + margin // right
                1 -> y += spaceSizePx + margin // down
                2 -> x -= spaceSizePx + margin // left
                3 -> y -= spaceSizePx + margin // up
            }

            stepsTaken++

            // Check if we need to change direction
            if (stepsTaken >= steps) {
                direction = (direction + 1) % 4
                stepsTaken = 0
                directionChanges++

                // Decrease steps after every 2 direction changes (spiral inward)
                if (directionChanges % 2 == 0 && steps > 1) {
                    steps--
                }
            }
        }

        return positions
    }

    private fun showTeamTooltip(teamPosition: com.hit.aliasgameapp.data.model.TeamPosition) {
        val members = viewModel.getMembersList(teamPosition)
        val currentPlayer = viewModel.getCurrentPlayer(teamPosition)

        if (members.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.no_members_listed), Toast.LENGTH_SHORT).show()
            return
        }

        // Create tooltip dialog
        val dialogView = layoutInflater.inflate(R.layout.dialog_team_tooltip, null)
        val dialog = android.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        val tvTeamName = dialogView.findViewById<android.widget.TextView>(R.id.tvTooltipTeamName)
        val tvMembers = dialogView.findViewById<android.widget.TextView>(R.id.tvTooltipMembers)
        val btnClose = dialogView.findViewById<android.widget.Button>(R.id.btnCloseTooltip)

        tvTeamName.text = teamPosition.teamName
        tvTeamName.setTextColor(ContextCompat.getColor(requireContext(), getColorResourceId(teamPosition.teamColor)))

        // Build members list with highlighting
        val membersText = buildString {
            members.forEachIndexed { index, member ->
                if (member == currentPlayer) {
                    append("👉 ")
                }
                append(member)
                if (member == currentPlayer) {
                    append(" ⭐")
                }
                if (index < members.size - 1) {
                    append("\n")
                }
            }
        }

        tvMembers.text = membersText

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun getColorResourceId(colorName: String): Int {
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

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

