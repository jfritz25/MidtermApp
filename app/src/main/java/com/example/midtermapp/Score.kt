package com.example.midtermapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    (tableName = "scores_table2")
data class Score(
    @PrimaryKey(autoGenerate = true)
    var scoreId:  Long = 0L,
    @ColumnInfo(name = "playerName")
    var player: String = "",
    @ColumnInfo (name = "score")
    var score: String = ""
)