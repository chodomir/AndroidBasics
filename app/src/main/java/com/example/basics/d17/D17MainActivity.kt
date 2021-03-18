package com.example.basics.d17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.basics.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class D17MainActivity : AppCompatActivity()/*, Callback<List<Repo>>*/ {
    companion object {
        const val TAG = "D17MainActivity"
        const val GITHUB_API_URL = "https://api.github.com/"
    }

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d17_main)

        val lvRepos = findViewById<ListView>(R.id.d17_lvRepos)

        val retrofit = with(Retrofit.Builder()) {
            baseUrl(GITHUB_API_URL)
            addConverterFactory(MoshiConverterFactory.create())
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            build()
        }

        val service = retrofit.create(GithubService::class.java)
        val repos = service.listRepos("chodomir")

        disposable = repos.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                { list -> lvRepos.adapter = ArrayAdapter<Repo>(this, R.layout.d17_simple_list_item, list) },
                                { t -> Log.d(TAG, "Error: ${t.message}") }
                            )
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable.dispose()
    }
}