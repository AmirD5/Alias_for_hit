package com.hit.aliasgameapp.ui.addedit

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.data.model.Team
import com.hit.aliasgameapp.databinding.FragmentAddEditBinding
import com.hit.aliasgameapp.viewmodel.TeamViewModel
import java.io.File
import java.io.FileOutputStream

class AddEditFragment : Fragment() {

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamViewModel by activityViewModels()

    private var currentPhotoPath: String? = null
    private var editingTeamId: Int = -1

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { copyImageToInternalStorage(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editingTeamId = arguments?.getInt("teamId", -1) ?: -1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            currentPhotoPath = savedInstanceState.getString("currentPhotoPath")
        }
        // Setup color spinner
        val colorArray = resources.getStringArray(R.array.team_colors)
        val colorAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, colorArray)
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerColor.adapter = colorAdapter

        if (savedInstanceState == null && editingTeamId > 0) {
            val team = viewModel.getTeamById(editingTeamId)
            team?.let {
                binding.etName.setText(it.name)
                binding.etMembers.setText(it.members)
                // Set spinner selection based on saved color
                val colorIndex = colorArray.indexOf(it.color)
                if (colorIndex >= 0) {
                    binding.spinnerColor.setSelection(colorIndex)
                }
                binding.etNotes.setText(it.notes)
                currentPhotoPath = it.imagePath
                }
            }
        if (currentPhotoPath != null) {
            binding.ivCardImage.setImageURI(currentPhotoPath!!.toUri())
        }

        binding.btnPickImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val color = binding.spinnerColor.selectedItem.toString()
            val notes = binding.etNotes.text.toString().trim()
            val members = binding.etMembers.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(requireContext(),
                    getString(R.string.team_name_is_required), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (currentPhotoPath == null) {
                Toast.makeText(requireContext(), getString(R.string.photo_required), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val team = Team(
                id = if (editingTeamId > 0) editingTeamId else 0,
                name = name,
                color = color,
                members = members,
                notes = notes,
                imagePath = currentPhotoPath
            )

            if (editingTeamId > 0) {
                viewModel.update(team)
            } else {
                viewModel.insert(team)
            }

            findNavController().navigateUp()
        }
    }

    private fun copyImageToInternalStorage(uri: Uri) {
        try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val file = File(requireContext().filesDir, "team_photo_${System.currentTimeMillis()}.jpg")
            FileOutputStream(file).use { output -> inputStream?.copyTo(output) }
            currentPhotoPath = file.absolutePath
            binding.ivCardImage.setImageURI(uri)
        } catch (_: Exception) {
            Toast.makeText(requireContext(),
                getString(R.string.failed_to_save_photo), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentPhotoPath", currentPhotoPath)
    }
}