package com.example.github.data

import com.example.github.domain.ReposContainer
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubWebservice {

    @GET("search/repositories?sort=stars&order=desc&q=android")
    fun loadTrendingRepos(@Query("page") page: Int): Single<ReposContainer>
}