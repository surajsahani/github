package com.example.github.data


interface ISchedulersProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}