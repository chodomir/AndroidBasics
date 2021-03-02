package com.example.basics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get buttons
        val btnExample1: Button = findViewById(R.id.btnExample1)
        val btnExample2: Button = findViewById(R.id.btnExample2)

        // set click listeners
        btnExample1.setOnClickListener {
            val intent = Intent(this, PrimaryActivity::class.java)
            startActivity(intent)
        }
    }
}