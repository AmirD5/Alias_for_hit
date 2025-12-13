package com.hit.aliasgameapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hit.aliasgameapp.data.model.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM teams ORDER BY id DESC")
    fun getAllTeams(): LiveData<List<Team>>

    @Query("SELECT * FROM teams WHERE id = :id")
    fun getTeamById(id: Int): Team?

    @Insert
    suspend fun insert(team: Team)

    @Update
    suspend fun update(team: Team)

    @Delete
    suspend fun delete(team: Team)
}