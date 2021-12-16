package com.example.github.data

import com.example.github.domain.RepositoryContract


@Module
class TrendingReposRepositoryModule {
    @Provides
    fun provideTrendingReposRepository(repository: TrendingReposRepository):
            RepositoryContract.ITrendingReposRepository = repository
}