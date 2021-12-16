package com.example.github.ui.trendings


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.github.data.Error
import com.example.github.data.ISchedulersProvider
import com.example.github.domain.Repo
import com.example.github.domain.RepositoryContract
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


class TrendingReposViewModel(
    private val repository: RepositoryContract.ITrendingReposRepository,
    private val schedulers: ISchedulersProvider
) : ViewModel() {
    private var page: Int = 1
    val errorsLiveData: LiveData<Error> by lazy {
        MutableLiveData<Error>()
    }
    val reposLiveData: LiveData<MutableList<Repo>> by lazy {
        MutableLiveData<MutableList<Repo>>()
    }

    fun loadTrendingRepos(reset: Boolean = false) {
        if (reset) {
            page = 1
        }
        repository
                .loadTrendingRepos(page)
                .observeOn(schedulers.ui())
                .subscribe({
                    (errorsLiveData as MutableLiveData<*>).value = Error.SUCCESS
                    (reposLiveData as MutableLiveData<*>).value = it.repos
                    page++
                }, { error ->
                    (errorsLiveData as MutableLiveData<*>).value = when (error) {
                        is HttpException -> {
                            if (error.code() == 422) {
                                Error.NO_MORE_DATA
                            } else {
                                Error.UNKNOWN
                            }
                        }
                        is SocketTimeoutException -> Error.TIMEOUT
                        is IOException -> Error.DISCONNECTED
                        else -> Error.UNKNOWN
                    }
                })
    }
}