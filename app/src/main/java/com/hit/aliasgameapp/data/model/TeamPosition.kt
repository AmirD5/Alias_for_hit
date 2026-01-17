package com.hit.aliasgameapp.data.model

data class TeamPosition(
    val teamId: Int,
    val teamName: String,
    val teamColor: String,
    val members: String,
    val currentPosition: Int = 0,
    val currentPlayerIndex: Int = 0 // Index of member describing words
)

