package com.example.github.ui.trendings

import com.example.github.data.ISchedulersProvider
import com.example.github.domain.RepositoryContract

@Module
class TrendingReposModule {

    @Provides
    fun provideTrendingReposViewModelFactory(repository: RepositoryContract.ITrendingReposRepository, schedulers: ISchedulersProvider) =
            TrendingReposViewModelFactory(repository, schedulers)
}