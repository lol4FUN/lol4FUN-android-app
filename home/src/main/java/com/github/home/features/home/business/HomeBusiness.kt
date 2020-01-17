package com.github.home.features.home.business

import com.github.home.repository.HomeRepository
import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.core.model.MatchList
import kotlinx.coroutines.delay
import org.koin.core.inject


class HomeBusiness(private val listener: HomeBusinessListener?) : BaseBusiness() {

    private val repository: HomeRepository by inject()

    fun getUserFirestore(): Customer? {
        return repository.getUserFirestore()
    }

    suspend fun getCurrentGame(encryptedSummonerId: String) {
        delay(1000)
        //TODO()
    }

    suspend fun getHistory(
        accountId: String,
        startIndex: Int,
        endIndex: Int,
        onResponse: (MatchList) -> Unit
    ) {
        delay(1000)
        //TODO
    }

    suspend fun getDetailMatch(gameId: Long, onResponse: (Match) -> Unit) {
        delay(1000)
        //TODO
    }
}