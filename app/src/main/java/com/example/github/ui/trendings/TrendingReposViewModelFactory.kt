package com.example.github.ui.trendings


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.github.data.ISchedulersProvider
import com.example.github.domain.RepositoryContract

class TrendingReposViewModelFactory constructor(
    private val repository: RepositoryContract.ITrendingReposRepository,
    private val schedulers: ISchedulersProvider
) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrendingReposViewModel(repository, schedulers) as T
    }
}