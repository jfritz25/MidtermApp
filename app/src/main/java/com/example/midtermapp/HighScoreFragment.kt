package com.example.midtermapp

import HighScoreAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.midtermapp.databinding.FragmentGameBinding
import com.example.midtermapp.databinding.FragmentHighscoreBinding
import com.example.midtermapp.databinding.FragmentMainBinding

class HighScoreFragment: Fragment() {
    val TAG = "HSFragment"
    private var _binding: FragmentHighscoreBinding? = null
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
        _binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = GameDatabase.getInstance(application).gameDao
        val viewModelFactory = GameViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            requireActivity(), viewModelFactory).get(GameViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner


        fun yesPressed(scoreId : Long) {
            Log.d(TAG, "in yesPressed(): scoreId = $scoreId")
            viewModel.deleteFromDB(scoreId,dao)
        }

        fun deleteClicked (scoreId : Long) {
            DeleteDialog(scoreId,::yesPressed).show(childFragmentManager,
                DeleteDialog.TAG)
        }

        val adapter = HighScoreAdapter(::deleteClicked)
        binding.hsList.adapter = adapter

        viewModel.allHighScores.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.allHighScores.observe(viewLifecycleOwner, Observer {
            if (viewModel.allHighScores.value!!.isEmpty()) {
                binding.hsList.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.hsList.visibility = View.VISIBLE
                binding.emptyView.visibility = View.GONE
            }
        })
        fun backToMain(){
            val action = HighScoreFragmentDirections.actionHsToMain()
            findNavController().navigate(action)
        }
        binding.BackToMain.setOnClickListener { backToMain() }




        return view
    }
}