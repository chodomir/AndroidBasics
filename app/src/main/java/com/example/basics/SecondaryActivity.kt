package com.example.basics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

// Secondary Activity has launchMode="singleTop"
class SecondaryActivity : AppCompatActivity() {
    companion object {
        val TAG = "SecondaryActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val btnToMain: Button = findViewById(R.id.btnSecMain)
        val btnToSecondary: Button = findViewById(R.id.btnSecSecondary)
        val btnToTertiary: Button = findViewById(R.id.btnSecTertiary)

        // Set button click listeners
        btnToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnToSecondary.setOnClickListener {
            val intent = Intent(this, SecondaryActivity::class.java)
            startActivity(intent)
        }
        btnToTertiary.setOnClickListener {
            val intent = Intent(this, TertiaryActivity::class.java)
            startActivity(intent)
        }

        Log.d(TAG, "onCreate() method called.")
    }

    override fun onStart() {
        Log.d(TAG, "onStart() method called.")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume() method called.")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause() method called.")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop() method called.")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy() method called.")
        super.onDestroy()
    }
}