package com.example.github.domain


interface RepositoryContract {
    interface ITrendingReposRepository {
        fun loadTrendingRepos(page: Int): Single<ReposContainer>
    }
}