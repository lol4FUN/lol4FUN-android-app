package com.github.home.repository

import com.github.lol4fun.core.api.Resource
import com.github.lol4fun.core.base.BaseRepository

class HomeRepository : BaseRepository() {

    suspend fun getMatchListById(
        encryptedAccountId: String,
        beginIndex: Int,
        endIndex: Int
    ): Resource {
        return safeApiCall(
            call = {
                api.getMachListByEncryptedAccountIdAsync(encryptedAccountId, beginIndex, endIndex)
            }
        )
    }

    suspend fun getDetailMatchById(matchId: Long): Resource {
        return safeApiCall(
            call = { api.getMatchByIdAsync(matchId) }
        )
    }

    suspend fun getCurrentGameInfoById(encryptedSummonerId: String): Resource {
        return safeApiCall(
            call = { api.getCurrentGameByEncryptedSummonerIdAsync(encryptedSummonerId) }
        )
    }
}