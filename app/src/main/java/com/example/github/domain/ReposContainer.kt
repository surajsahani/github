package com.example.github.domain

import com.example.github.domain.Repo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReposContainer(
        @Expose @SerializedName("total_count") val totalCount: Int,
        @Expose @SerializedName("items") val repos: List<Repo>
)