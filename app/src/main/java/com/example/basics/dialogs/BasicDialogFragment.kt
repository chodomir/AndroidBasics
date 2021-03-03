package com.example.basics.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.basics.R
import java.lang.IllegalStateException

class BasicDialogFragment : DialogFragment() {
    internal lateinit var listener: BasicDialogListener

    interface BasicDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    companion object {
        val TAG: String = "BasicDialogFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as BasicDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement BasicDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.app_name)
                    .setPositiveButton(R.string.done,
                    DialogInterface.OnClickListener { _, id ->
                        Log.d(TAG, "Positive button pressed, id: $id")
                        // Send the positive button event back to the host activity
                        listener.onDialogPositiveClick(this)
                    })
                    .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { _, id ->
                        Log.d(TAG, "Negative button pressed, id: $id")
                        // Send the positive button event back to the host activity
                        listener.onDialogNegativeClick(this)
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}