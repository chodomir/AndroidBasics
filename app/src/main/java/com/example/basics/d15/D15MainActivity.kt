package com.example.basics.d15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.basics.R

class D15MainActivity : AppCompatActivity() {
    private lateinit var mHandler: Handler
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mProgressText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d15_main)

        // init values
        mHandler = Handler(Looper.getMainLooper())
        mProgressBar = findViewById(R.id.d15_pbLoading)
        mProgressText = findViewById(R.id.d15_tvProgress)
    }

    override fun onStart() {
        super.onStart()

        startProgressHandlerStyle()
    }

    private fun startProgressHandlerStyle() {
        val threadTag = "D15Thread"
        val max = 45
        val timeout = 3000L / max
        Thread(Runnable {
            for (i in 1..max) {
                // wait a little bit
                Thread.sleep(timeout)
                Log.d(threadTag, "Current value: $i")
                // update the progress bar
                mHandler.post {
                    mProgressText.text = "${((i / max.toFloat()) * 100).toInt()} %"
                    if (i == max) {
                        mProgressBar.visibility = View.GONE
                        mProgressText.text = getString(R.string.done)
                    }
                }
            }
            Log.d(threadTag, "Finished!")
        }).start()
    }
}