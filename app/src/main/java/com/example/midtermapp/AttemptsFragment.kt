package com.example.midtermapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.midtermapp.databinding.FragmentAttemptsBinding
import com.example.midtermapp.databinding.FragmentGameBinding

class AttemptsFragment: Fragment() {
     /**
     * This fragment is the bottom fragment of our GameScreen and increments guesses as they're entered
     * */
    val TAG = "AttemptsFragment"
    private var _binding: FragmentAttemptsBinding? = null
    private val binding get() = _binding!!

     override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = FragmentAttemptsBinding.inflate(inflater, container, false)
    val view = binding.root
    val application = requireNotNull(this.activity).application
    val dao = GameDatabase.getInstance(application).gameDao
    val viewModelFactory = GameViewModelFactory(dao)
    val viewModel = ViewModelProvider(
        requireActivity(), viewModelFactory).get(GameViewModel::class.java)
    binding.lifecycleOwner = viewLifecycleOwner
         /**
          * Sets the number of attempts based on changes in the guesses variable in the viewModel
          */
     viewModel.guesses.observe(viewLifecycleOwner, Observer {
         it?.let {
             binding.NumOfAttempts.text = "Number of attempts: ${it}"
         }
     })


    return view

}


override fun onDestroyView() {
    /**
     * Sets binding to null when view is destroyed
     */
    super.onDestroyView()
    _binding = null
}
}