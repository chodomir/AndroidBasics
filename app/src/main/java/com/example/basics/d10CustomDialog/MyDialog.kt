package com.example.basics.d10CustomDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.basics.R

class MyDialog(context: Context) : Dialog(context, R.style.D10MyDialog) {
    companion object {
        val TAG = "MyDialog"
    }

    lateinit var btnPositive: Button
    lateinit var btnNegative: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.d10_my_dialog)

        setupButtons()
    }

    private fun setupButtons() {
        btnPositive = findViewById(R.id.d10_button_positive)
        btnNegative = findViewById(R.id.d10_button_negative)

        btnPositive.setOnClickListener {
            Log.d(TAG, "Positive button clicked")
            dismiss()
        }
        btnNegative.setOnClickListener {
            Log.d(TAG, "Negative button clicked")
            dismiss()
        }
    }


}