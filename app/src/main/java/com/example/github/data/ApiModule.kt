package com.example.github.data

import com.example.github.API_REST_URL


@Module
class ApiModule {

    @Provides
    @Singleton
    @Named(NAMED_REST_API_URL)
    fun provideRestApiUrl(): String = API_REST_URL

    @Provides
    fun provideSchedulersProvider(): ISchedulersProvider =
            SchedulersProvider()

}