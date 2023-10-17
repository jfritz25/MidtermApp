package com.example.midtermapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.midtermapp.databinding.FragmentMainBinding

class MainFragment: Fragment() {
    /**
     * This is our first screen displayed and presents the user two options, to play and to view highscores
     */
    val TAG = "MainFragment"
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        /**
         * The onCreateView function is overridden to take the arguments from the noteId and set the view model
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
        val args: MainFragmentArgs by navArgs()
        var playerName = args.playerName
        var score = args.score

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
    /**
     * The following checks to see if the user had played once before, if they had it'll update the welcome text fragment
     * to display their name and previous score
     */
        if(score != ""){
            binding.Welcome.text = "$playerName score: ${score[0]} \n Play another game? "
        }

        binding.PlayButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainToGame()
            findNavController().navigate(action)
        }

        binding.HighScoreButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainToHs()
            findNavController().navigate(action)
        }

        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}