package com.example.basics.d15

import android.os.AsyncTask
import android.util.Log

const val TAG = "DownloadTask"

class DownloadTask : AsyncTask<Int, Int, Int>() {
    override fun doInBackground(vararg params: Int?): Int {
        var totalSize = 0
        for (i in params.indices) {
            totalSize += params[i] ?: 0
            Log.d(TAG, "(Background) Processing param with value ${params[i]}...")
            publishProgress((i / (params.size - 1).toFloat() * 100).toInt())
            Thread.sleep(600)
            Log.d(TAG, "Finished!")
        }
        return totalSize
    }

    override fun onProgressUpdate(vararg values: Int?) {
        Log.d(TAG, "(UI Thread) Progress: ${values[0]}%")
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)

        Log.d(TAG, "Background tasks finished! Total size: $result")
    }
}