package com.example.github.domain

import android.util.Log

interface UserProfileUseCase {
    suspend fun loadUserProfile(input: String)
}

class UserProfileUseCaseImpl(
    private val userProfileRepository: UserProfileRepository
) : UserProfileUseCase {

    override suspend fun loadUserProfile(input: String) {
        val userProfile = userProfileRepository.loadUserProfileData(input)
        Log.d("@@@@@ testing name:", userProfile.name)
    }

}