package com.example.midtermapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GameViewModel(dao: GameDao) : ViewModel() {
    /**
     * This is our main view model for the app and will handle all functionality between the two fragments on the game screen
     * as well as any database interaction
     */

    val answer = Random.nextInt(1,101)
    var playerName = ""
    var allHighScores: MutableLiveData<List<Score>> = MutableLiveData(emptyList())

    /**
     * The below section of code ensures that allHighScores gets set and that the getAll() doesn't wait to be fully executed
     */
    init {
       dao.getAll().observeForever{it ->
           if(it != null) {
               allHighScores.postValue(it)
           }
        }
    }
    var guesses = MutableLiveData<Int>().apply { value = 0 }



    fun SetName(name: String){
        playerName = name

    }

    fun IncrementGuesses() {
        guesses.value = guesses.value?.plus(1)
    }

    /**
     * Enters the score into the highscore database
     */
    fun sendToDB(dao: GameDao){
        val score = Score()
        score.player = playerName
        score.score = guesses.value.toString()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.insert(score)
            }
        }

    }

    /**
     * Deletes the score from the highscore DB
     */
    fun deleteFromDB(scoreId: Long, dao: GameDao)
    {
        viewModelScope.launch {
            val score = Score()
            score.scoreId = scoreId
            withContext(Dispatchers.IO) {
                dao.delete(score)
            }
        }
    }


}





