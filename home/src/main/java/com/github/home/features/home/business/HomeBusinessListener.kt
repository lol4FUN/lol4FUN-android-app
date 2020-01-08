package com.github.home.features.home.business

interface HomeBusinessListener {

    fun onSuccessFetchHistory()

    fun onErrorFetchHistory(error: String?)
}