package com.example.github.domain

import com.example.github.domain.models.UserProfile

interface UserProfileRepository {
    suspend fun loadUserProfileData(input: String): UserProfile
}