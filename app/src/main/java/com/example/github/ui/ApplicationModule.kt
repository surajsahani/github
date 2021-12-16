package com.example.github.ui


@Module
class ApplicationModule {

    @Provides
    fun provideActionManager(): ActionManager = ActionManager.instance
}