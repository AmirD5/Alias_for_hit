package com.hit.aliasgameapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val color: String,
    val members: String = "",
    val notes: String = "",
    val imagePath: String? = null
)