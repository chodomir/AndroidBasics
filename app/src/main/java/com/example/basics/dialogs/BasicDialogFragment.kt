package com.example.basics.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.basics.R
import java.lang.IllegalStateException

class BasicDialogFragment : DialogFragment() {
    companion object {
        val TAG: String = "BasicDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.app_name)
                    .setPositiveButton(R.string.done,
                    DialogInterface.OnClickListener { _, id ->
                        Log.d(TAG, "Positive button pressed, id: $id")
                    })
                    .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { _, id ->
                        Log.d(TAG, "Negative button pressed, id: $id")
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}