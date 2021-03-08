package com.example.basics.d10CustomDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.basics.R

class MyDialog(context: Context) : Dialog(context, R.style.D10MyDialog) {
    companion object {
        val TAG = "MyDialog"
    }

    var floatingContextMenu: Boolean = true

    lateinit var btnPositive: Button
    lateinit var btnNegative: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.d10_my_dialog)

        setupButtons()
    }

    private fun setupButtons() {
        val radioButton1: RadioButton = findViewById(R.id.d10_radio_button_1)
        val radioButton2: RadioButton = findViewById(R.id.d10_radio_button_2)
        btnPositive = findViewById(R.id.d10_button_positive)
        btnNegative = findViewById(R.id.d10_button_negative)

        radioButton1.setOnClickListener { onRadioButtonClicked(it) }
        radioButton2.setOnClickListener { onRadioButtonClicked(it) }

        btnPositive.setOnClickListener {
            Log.d(TAG, "Positive button clicked")
            dismiss()
        }
        btnNegative.setOnClickListener {
            Log.d(TAG, "Negative button clicked")
        }
    }

    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            floatingContextMenu = (view.id == R.id.d10_radio_button_1 && checked)
            Log.d(TAG,"Radio button clicked: floating context menu = $floatingContextMenu")
        }
    }

    fun shouldShowFloatingContextMenu() : Boolean {
        return floatingContextMenu
    }


}