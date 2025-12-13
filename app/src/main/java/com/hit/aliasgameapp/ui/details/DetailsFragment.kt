package com.hit.aliasgameapp.ui.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.databinding.FragmentDetailsBinding
import com.hit.aliasgameapp.viewmodel.TeamViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamViewModel by activityViewModels()
    private var teamId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamId = arguments?.getInt("teamId", -1) ?: -1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (teamId == -1) {
            findNavController().navigateUp()
            return
        }

        val team = viewModel.getTeamById(teamId) ?: run {
            findNavController().navigateUp()
            return
        }

        binding.tvDetailName.text = team.name
        binding.tvDetailColor.text = team.color.ifEmpty { "No color set" }
        binding.tvDetailNotes.text = team.notes.ifEmpty { "No notes" }

        // Apply the team color to the team name
        val colorResId = getColorResourceId(team.color)
        val color = ContextCompat.getColor(requireContext(), colorResId)
        binding.tvDetailName.setTextColor(color)

        if (team.imagePath != null) {
            binding.ivDetailImage.setImageURI(team.imagePath.toUri())
        } else {
            binding.ivDetailImage.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        binding.btnEdit.setOnClickListener {
            val bundle = Bundle().apply { putInt("teamId", teamId) }
            findNavController().navigate(R.id.addEditFragment, bundle)
        }
    }

    private fun getColorResourceId(colorName: String): Int {
        return when (colorName) {
            "Red" -> R.color.team_red
            "Blue" -> R.color.team_blue
            "Green" -> R.color.team_green
            "Yellow" -> R.color.team_yellow
            "Orange" -> R.color.team_orange
            "Purple" -> R.color.team_purple
            "Pink" -> R.color.team_pink
            "Teal" -> R.color.team_teal
            else -> R.color.black
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}