package com.example.midtermapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment


class DeleteDialog(val scoreId : Long,val clickListener: (scoreId: Long) -> Unit) : DialogFragment() {
    /**
     * this class is used for the delete button and the prompting of the user to confirm or deny their selection
     * @param noteId
     * @param clickListener
     *
     * @return the item is either deleted ("yes" is pressed) from the database or left alon ("no" is pressed)
     */
    val TAG = "ConfirmDeleteDialogFragment"
    interface myClickListener {
        fun yesPressed()
    }
    var listener: myClickListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_confirmation))
            .setPositiveButton(getString(R.string.yes)) { _,_ -> clickListener(scoreId)}
            .setNegativeButton(getString(R.string.no)) { _,_ -> }

            .create()

    companion object {
        const val TAG = "ConfirmDeleteDialogFragment"
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as myClickListener
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
    }
}