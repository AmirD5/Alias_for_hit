package com.hit.aliasgameapp.ui.mainlist

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.data.model.Team
import com.hit.aliasgameapp.databinding.ItemTeamBinding

class TeamAdapter(
    private val onClick: (Team) -> Unit,
    private val onDelete: (Team) -> Unit
) : ListAdapter<Team, TeamAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team) {
            binding.tvName.text = team.name
            binding.tvColor.text = team.color.ifEmpty { "No color" }

            // Apply the team color to the team name and color indicator
            val colorResId = getColorResourceId(team.color)
            val color = ContextCompat.getColor(binding.root.context, colorResId)
            binding.tvName.setTextColor(color)
            binding.colorIndicator.setBackgroundColor(color)

            if (team.imagePath != null) {
                binding.ivImage.setImageURI(team.imagePath.toUri())
            } else {
                binding.ivImage.setImageResource(android.R.drawable.ic_menu_gallery)
            }

            binding.btnDelete.setOnClickListener { onDelete(team) }
            binding.root.setOnClickListener { onClick(team) }
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
    }

    class DiffCallback : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Team, newItem: Team) = oldItem == newItem
    }
}