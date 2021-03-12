package com.example.basics.d15

import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.basics.R
import java.lang.ref.WeakReference

const val TAG = "DownloadTask"

class ValuesTask(private val activityRef: WeakReference<Activity>) : AsyncTask<Int, Int, Int>() {
    private var tvProgress: TextView? = null
    private var pbLoading: ProgressBar? = null

    override fun onPreExecute() {
        super.onPreExecute()

        tvProgress = activityRef.get()?.findViewById(R.id.d15_tvProgress)
        pbLoading = activityRef.get()?.findViewById(R.id.d15_pbLoading)
    }

    override fun doInBackground(vararg params: Int?): Int {
        var totalValue = 0
        for (i in params.indices) {
            totalValue += params[i] ?: 0
            Log.d(TAG, "(Background) Processing param with value ${params[i]}...")
            publishProgress((i / (params.size - 1).toFloat() * 100).toInt())
            Thread.sleep(600)
            Log.d(TAG, "Finished!")

            if (isCancelled()) break;
        }
        return totalValue
    }

    override fun onProgressUpdate(vararg values: Int?) {
        Log.d(TAG, "(UI Thread) Progress: ${values[0]}%")
        tvProgress?.text = "${values[0]}%"
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)

        tvProgress?.text = "Total value: $result"
        pbLoading?.visibility = View.GONE
        Log.d(TAG, "Background tasks finished! Summed values: $result")
    }
}