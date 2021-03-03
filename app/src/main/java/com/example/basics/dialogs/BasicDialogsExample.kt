package com.example.basics.dialogs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.basics.R
import com.google.android.material.snackbar.Snackbar

class BasicDialogsExample : AppCompatActivity(),
        BasicDialogFragment.BasicDialogListener {
    companion object {
        val TAG: String = "BasicDialogsExample"
    }

    class MyUndoListener(var tvText: TextView) : View.OnClickListener {
        override fun onClick(v: View?) {
            tvText.setTextColor(Color.BLACK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_dialogs_example)

        val tvColoredText: TextView = findViewById(R.id.tvColoredText)
        tvColoredText.text = "Hold to change color"
        tvColoredText.setTextColor(Color.BLACK)
        tvColoredText.setOnLongClickListener {
            tvColoredText.setTextColor(Color.MAGENTA)

            // snackbar time
            Snackbar.make(
                findViewById(R.id.clContainer),
                R.string.done,
                Snackbar.LENGTH_LONG
            ).setAction(R.string.cancel, MyUndoListener(tvColoredText))
                .show()

            return@setOnLongClickListener true
        }
    }

    override fun onStart() {
        super.onStart()

        val basicdf = BasicDialogFragment()
        basicdf.show(supportFragmentManager, "BasicDialog FragmentManager")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Log.d(TAG, "Positive button clicked (host activity)")
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Log.d(TAG, "Negative button clicked (host activity)")
    }
}