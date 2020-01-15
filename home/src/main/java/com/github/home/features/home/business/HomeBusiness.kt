package com.github.home.features.home.business

import com.github.home.repository.HomeRepository
import com.github.lol4fun.core.base.BaseBusiness
import kotlinx.coroutines.delay
import org.koin.core.inject


class HomeBusiness(private val listener: HomeBusinessListener?): BaseBusiness() {

    private val repository: HomeRepository by inject()

    suspend fun getCurrentGame() {
        delay(1000)
        //listener.onSuccessFetchCurrentGame()
        //TODO()
    }

    suspend fun getHistory() {
        delay(1000)
        //listener.onSuccessFetchCurrentGame()
        //TODO
    }

    suspend fun getDetailMatch(gameId: Long) {
        delay(1000)
        //TODO
    }
}