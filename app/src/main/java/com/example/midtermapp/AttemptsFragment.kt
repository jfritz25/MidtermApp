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
    val TAG = "AttemptsFragment"
    private var _binding: FragmentAttemptsBinding? = null
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
    _binding = FragmentAttemptsBinding.inflate(inflater, container, false)
    val view = binding.root
    val application = requireNotNull(this.activity).application
    val dao = GameDatabase.getInstance(application).gameDao
    val viewModelFactory = GameViewModelFactory(dao)
    val viewModel = ViewModelProvider(
        requireActivity(), viewModelFactory).get(GameViewModel::class.java)
    binding.lifecycleOwner = viewLifecycleOwner

     viewModel.guesses.observe(viewLifecycleOwner, Observer {
         it?.let {
             binding.NumOfAttempts.text = "Number of attempts: ${it}"
         }
     })


    return view

}


override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}