package com.example.basics.dialogs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.basics.R
import com.google.android.material.snackbar.Snackbar

class BasicSnackbar : AppCompatActivity() {

    class MyUndoListener(var tvText: TextView) : View.OnClickListener {
        override fun onClick(v: View?) {
            tvText.setTextColor(Color.BLACK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_snackbar)

        val tvColoredText: TextView = findViewById(R.id.tvColoredText)
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
}