package com.hit.aliasgameapp.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.databinding.FragmentResultBinding
import android.content.Intent

data class ScoreData(val teamName: String, val score: Int)
data class ScoreResponse(val id: String, val createdAt: String)


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val winnerName = arguments?.getString("winnerName") ?: "Unknown Team"
        val score = arguments?.getInt("score") ?: 0

        binding.textViewWinnerName.text = winnerName

        binding.buttonHome.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_mainListFragment)
        }

        binding.buttonUploadScore.setOnClickListener {
            shareVictory(winnerName, score)
        }
        binding.buttonUploadScore.text = getString(R.string.share_your_win_)
    }
    private fun shareVictory(winnerName: String, score: Int) {
        val shareText =
            getString(R.string.i_just_won_in_alias_team_score_can_you_beat_me, winnerName, score)

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent,
            getString(R.string.share_your_victory_via))
        startActivity(shareIntent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}