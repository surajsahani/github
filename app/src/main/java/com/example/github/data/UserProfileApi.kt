package com.example.github.data

import com.example.github.data.models.UserProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserProfileApi {
    @GET("/users/{username}")
    suspend fun getUserProfile(@Path("username") username: String): UserProfileResponse

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}