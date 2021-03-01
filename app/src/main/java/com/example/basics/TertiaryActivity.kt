package com.example.basics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.commit
import androidx.fragment.app.replace

// TertiaryActivity has launchMode="singleTask"
class TertiaryActivity : AppCompatActivity() {
    companion object {
        val TAG = "TertiaryActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tertiary)

        supportFragmentManager.commit {
            replace<UIFragment>(R.id.fragmentContainer3)
            setReorderingAllowed(true)
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