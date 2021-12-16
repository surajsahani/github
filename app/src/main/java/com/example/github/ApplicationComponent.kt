package com.example.github

import androidx.core.view.KeyEventDispatcher
import com.example.github.data.ApiModule
import com.example.github.ui.ApplicationModule
import com.example.github.ui.ContributorsModule


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApiServiceModule::class,
    ApiModule::class,
    ApplicationModule::class,
    ContributorsModule::class
])
interface ApplicationComponent : AndroidInjector<GithubApplication> {


    @KeyEventDispatcher.Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GithubApplication): Builder

        fun build(): ApplicationComponent
    }

}