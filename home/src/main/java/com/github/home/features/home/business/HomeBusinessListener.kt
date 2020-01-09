package com.github.home.features.home.business

import com.github.lol4fun.core.model.MatchList

interface HomeBusinessListener {

    fun onSuccessFetchMatch()

    fun onErrorFetchMatch(error: String?)

    fun onSuccessFetchHistory(matches: MatchList)

    fun onErrorFetchHistory(error: String?)
}