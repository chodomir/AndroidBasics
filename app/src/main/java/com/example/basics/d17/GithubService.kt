package com.example.basics.d17

import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.rxjava3.core.Single

interface GithubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<Repo>>
}
