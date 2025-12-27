package com.hit.aliasgameapp.ui.mainlist

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.databinding.FragmentMainListBinding
import com.hit.aliasgameapp.viewmodel.TeamViewModel
import com.hit.aliasgameapp.util.LocaleHelper
class MainListFragment : Fragment() {

    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamViewModel by activityViewModels()
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TeamAdapter(
            onClick = { team ->
                val bundle = Bundle().apply { putInt("teamId", team.id) }
                findNavController().navigate(R.id.detailsFragment, bundle)
            },
            onDelete = { team ->
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.delete_card)
                    .setMessage(R.string.confirm_delete_message)
                    .setPositiveButton(R.string.delete) { _, _ -> viewModel.delete(team) }
                    .setNegativeButton(R.string.cancel, null)
                    .show()
            }
        )

        binding.recyclerView.adapter = adapter
        binding.btnAddTeam.setOnClickListener {
            val bundle = Bundle().apply { putInt("teamId", -1) }
            findNavController().navigate(R.id.addEditFragment, bundle)
        }

        viewModel.allTeams.observe(viewLifecycleOwner) { teams ->
            adapter.submitList(teams)
            binding.tvEmpty.visibility = if (teams.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.btnAbout.setOnClickListener {
            findNavController().navigate(R.id.aboutFragment)
        }

        binding.btnLanguageToggle.setOnClickListener {
            LocaleHelper.toggleLanguage(requireContext())
            requireActivity().recreate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}