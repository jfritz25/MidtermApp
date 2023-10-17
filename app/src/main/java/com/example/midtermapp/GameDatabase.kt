package com.example.midtermapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.midtermapp.GameDao
import com.example.midtermapp.Score
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Score::class], version = 2)
abstract class GameDatabase: RoomDatabase() {
    /**
     * Referencing the instance of the SQL Lite data base
     * @returns an instance of the Scores database
     */
    abstract val gameDao: GameDao
    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null
        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): GameDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, GameDatabase::class.java, "scores_database", ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}