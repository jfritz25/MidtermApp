package com.example.midtermapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    (tableName = "scores_table")
data class Score(
    @PrimaryKey(autoGenerate = true)
    var scoreId:  Long = 0L,
    @ColumnInfo(name = "note_name")
    var Player: String = "",
    @ColumnInfo (name = "body")
    var Score: String = ""
)