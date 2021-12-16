package com.example.github.data



class SchedulersProvider : ISchedulersProvider {
    override fun ui(): Scheduler =
            AndroidSchedulers.mainThread()

    override fun io(): Scheduler =
            Schedulers.io()

}