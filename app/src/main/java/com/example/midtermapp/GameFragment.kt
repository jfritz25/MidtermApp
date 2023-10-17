package com.example.midtermapp

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.midtermapp.databinding.FragmentGameBinding
import com.example.midtermapp.databinding.FragmentMainBinding

class GameFragment: Fragment() {
    /**
     * This framgent is the top portion of our GameScreen it handles the guessing, entering of the player name, and checking of answers submitted
     */
    val TAG = "GameFragment"
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        /**
         * The onCreateView function is overridden to take the arguments from the scoreId and set the view model
         *
         * @param inflater used to inflate the view model
         * @param container used in the binding containing the view grop
         * @param savedInstanceState holding the previous state of the object to reflect the current behaviors
         *
         * @returns the view
         * */
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = GameDatabase.getInstance(application).gameDao
        val viewModelFactory = GameViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            requireActivity(), viewModelFactory).get(GameViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        val answer = viewModel.answer
        val Right = MediaPlayer.create(context, R.raw.right)
        val Wrong = MediaPlayer.create(context, R.raw.wrong)


        /**
         * This function runs whenever the ok button is pressed. It checks the guess, increments the guess plays a toast
         *
         * If the player is right the viewModel increments the guess, set's the player name, adds the score to the database, and navigates back home
         */
        fun okPressed(){
            val guess = binding.Guess.text.toString().toInt()

            if(guess > answer) {Toast.makeText(activity, "Too High!", Toast.LENGTH_SHORT).show()
            Wrong.start()
                viewModel.IncrementGuesses()}

            else if(guess < answer) {Toast.makeText(activity, "Too Low!", Toast.LENGTH_SHORT).show()
            Wrong.start()
                viewModel.IncrementGuesses()}

            else {
                Toast.makeText(activity, "Nice job beast, you got it right!", Toast.LENGTH_SHORT).show()
                Right.start()
                viewModel.IncrementGuesses()

                viewModel.SetName(binding.PlayerName.text.toString())
                viewModel.sendToDB(dao)

                val action = MainGameFragmentDirections.actionGameToMain(binding.PlayerName.text.toString(), viewModel.guesses.value.toString())
                findNavController().navigate(action)
            }

        }

        /**
         * The following two functions increment/decrement the value in the edit text when the image buttons are pressed
         */
        fun decrement(){
            if(binding.Guess.text.toString() != "") {
                var newNum = binding.Guess.text.toString().toInt() - 1
                binding.Guess.setText(newNum.toString())
            }
        }

        fun increment() {
            if (binding.Guess.text.toString() != "") {
                var newNum = binding.Guess.text.toString().toInt() + 1
                binding.Guess.setText(newNum.toString())
            }
        }

        /**
         * Calls functions defined above using click listeners
         */
        binding.minusbutton.setOnClickListener {
            decrement()
        }
        binding.addbutton.setOnClickListener {
            increment()
        }
        binding.OkButton.setOnClickListener {
            okPressed()
        }
        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}