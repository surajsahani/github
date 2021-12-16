package com.example.github.data

import com.example.github.domain.ReposContainer
import com.example.github.domain.RepositoryContract


class TrendingReposRepository @Inject constructor(private val webservice: GithubWebservice,
                                                  private val schedulersProvider: ISchedulersProvider
)
    : RepositoryContract.ITrendingReposRepository {
    override fun loadTrendingRepos(page: Int): Single<ReposContainer> =
            webservice
                    .loadTrendingRepos(page)
                    .subscribeOn(schedulersProvider.io())
}