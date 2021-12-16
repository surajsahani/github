package com.example.github.data.models

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,
)
