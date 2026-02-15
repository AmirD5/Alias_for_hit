package com.hit.aliasgameapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hit.aliasgameapp.data.model.WordEntity

@Dao
interface WordDao {
    // Save the list of 240 words(30 cards)
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(words: List<WordEntity>)

    // This is the "Magic" function for your game!
    // It automatically grabs 8 RANDOM words from your saved list.
    @Query("SELECT * FROM words_table ORDER BY RANDOM() LIMIT 8")
    suspend fun getRandomCardWords(): List<WordEntity>

    // Check if we already have words saved
    @Query("SELECT COUNT(*) FROM words_table")
    suspend fun getWordCount(): Int

    // NEW: Function to remove the words we just used
    @Delete
    suspend fun deleteWords(words: List<WordEntity>)

    @Query("DELETE FROM words_table")
    suspend fun deleteAll()
}