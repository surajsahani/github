package com.example.github.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.UserProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: UserProfileUseCase
) : ViewModel() {

    fun onStartClick(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.loadUserProfile(input)
        }
    }
}