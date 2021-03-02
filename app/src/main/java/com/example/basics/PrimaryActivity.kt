package com.example.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf

// MainActivity launchMode set to "standard"
class PrimaryActivity : AppCompatActivity() {
    companion object {
        val TAG = "PrimaryActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary)

        val bundle = bundleOf("parent" to "Standard")
        supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainer, UIFragment::class.java, bundle)
                .commit()

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