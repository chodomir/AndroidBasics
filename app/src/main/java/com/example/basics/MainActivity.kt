package com.example.basics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.function.Consumer

// MainActivity launchMode set to "standard"
class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity"
        val LAUNCH_RESULT_ACITIVITY = 0
    }

    private lateinit var tvActivityResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get text view that displays return value of started activity
        tvActivityResult = findViewById(R.id.tvActivityResult)

        val btnToSecondary: Button = findViewById(R.id.btnMainSecondary)
        val btnToMain: Button = findViewById(R.id.btnMainMain)
        val btnToTertiary: Button = findViewById(R.id.btnMainTertiary)
        val btnToQuaternary: Button = findViewById(R.id.btnMainQuaternary)
        val btnToResultActivity: Button = findViewById(R.id.btnStartActivityForResult)

        // Set button click listeners
        btnToSecondary.setOnClickListener {
            val intent = Intent(this, SecondaryActivity::class.java)
            startActivity(intent)
        }
        btnToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnToTertiary.setOnClickListener {
            val intent = Intent(this, TertiaryActivity::class.java)
            startActivity(intent)
        }
        btnToQuaternary.setOnClickListener {
            val intent = Intent(this, QuaternaryActivity::class.java)
            startActivity(intent)
        }
        btnToResultActivity.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("data", "Hello World!")
            }
            startActivityForResult(intent, LAUNCH_RESULT_ACITIVITY)
        }

        Log.d(TAG, "onCreate() method called.")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult() method called.")
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            LAUNCH_RESULT_ACITIVITY ->
                tvActivityResult.text = data?.getStringExtra("result")
        }
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