package com.example.basics.d15

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.basics.R
import java.lang.ref.WeakReference

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
        //startProgressRunnableStyle()
        //startProgressAsyncTaskStyle()
    }

    private fun startProgressAsyncTaskStyle() {
        ValuesTask(WeakReference(this)).execute(1,3,5,12,4,12)
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
                mHandler.postDelayed ({
                    mProgressText.text = "${((i / max.toFloat()) * 100).toInt()} %"
                    if (i == max) {
                        mProgressBar.visibility = View.GONE
                        mProgressText.text = getString(R.string.done)
                    }
                }, 600)
            }
            Log.d(threadTag, "Finished!")
        }).start()
    }

    // we are expecting an exception to be thrown
    private fun startProgressRunnableStyle() {
        Thread(Runnable() {
            Thread.sleep(1000)
            mProgressText.text = "Hello World!"
        }).start()
    }
}