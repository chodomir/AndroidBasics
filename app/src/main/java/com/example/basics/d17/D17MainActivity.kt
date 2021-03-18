package com.example.basics.d17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.basics.R
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class D17MainActivity : AppCompatActivity(), Callback<List<Repo>> {
    companion object {
        const val TAG = "D17MainActivity"
        const val GITHUB_API_URL = "https://api.github.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d17_main)

        val retrofit = with(Retrofit.Builder()) {
            baseUrl(GITHUB_API_URL)
            addConverterFactory(MoshiConverterFactory.create())
            build()
        }

        val service = retrofit.create(GithubService::class.java)
        val repos = service.listRepos("chodomir")
        repos.enqueue(this)
    }

    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
        Log.d(TAG, "Request failed!")
    }

    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
        response.body()?.let {
            val lvRepos = findViewById<ListView>(R.id.d17_lvRepos)
            val adapter = ArrayAdapter<Repo>(this, R.layout.d17_simple_list_item, it)
            lvRepos.adapter = adapter
        } ?: Log.d(TAG, "Response empty!")
    }
}