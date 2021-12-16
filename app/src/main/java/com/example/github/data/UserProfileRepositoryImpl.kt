package com.example.github.data

import com.example.github.domain.UserProfileRepository
import com.example.github.domain.models.UserProfile
import com.example.github.domain.models.toUserProfile

class UserProfileRepositoryImpl(
    private val apiService: UserProfileApi
): UserProfileRepository {


    override suspend fun loadUserProfileData(input: String): UserProfile {
        return apiService.getUserProfile(input).toUserProfile()

    }
}