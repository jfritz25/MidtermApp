package com.example.midtermapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDao {
    /**
     * Used as the interface working with the database to insert, delete and update the main data
     * table holding the scores present
     *
     */
    @Insert
    fun insert(score: Score)
    @Update
    fun update(score: Score)
    @Delete
    fun delete(score: Score)
//    @Query("SELECT * FROM scores_table2 WHERE scoreId = :key")
//    fun get(key: Long): LiveData<Score>
    @Query("SELECT * FROM scores_table2 ORDER BY score ASC")
    fun getAll(): LiveData<List<Score>>


}