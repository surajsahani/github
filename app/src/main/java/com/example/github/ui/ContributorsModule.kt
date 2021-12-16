package com.example.github.ui

import com.example.github.data.TrendingReposRepositoryModule
import com.example.github.ui.trendings.TrendingReposFragment
import com.example.github.ui.trendings.TrendingReposModule


@Module
abstract class ContributorsModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [TrendingReposRepositoryModule::class, TrendingReposModule::class])
    abstract fun bindTrendingReposFragment(): TrendingReposFragment
}