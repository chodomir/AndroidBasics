package com.example.basics

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // get data from Intent
        val data = intent.getStringExtra("data")

        // update text view to string sent from other activity
        val tvSentData: TextView = findViewById(R.id.tvSentData)
        tvSentData.text = "ResultActivity with data: ${data}"

        // Return Activity result on button click
        val btnDone: Button = findViewById(R.id.btnFinish)
        btnDone.setOnClickListener {
            val intent = Intent().apply {
                putExtra("result", "This is a result")
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}