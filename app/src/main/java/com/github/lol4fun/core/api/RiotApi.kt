package com.github.lol4fun.core.api

import com.github.lol4fun.core.model.SummonerInfo
import com.github.lol4fun.core.model.dto.CurrentGameInfoDTO
import com.github.lol4fun.core.model.dto.MatchDTO
import com.github.lol4fun.core.model.dto.MatchListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotApi {

    @GET("summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummonerNameByNameAsync(
        @Path(value = "summonerName", encoded = true) summonerName: String
    ): Response<SummonerInfo>

    @GET("match/v4/matchlists/by-account/{encryptedAccountId}")
    suspend fun getMachListByEncryptedAccountIdAsync(
        @Path(value = "encryptedAccountId", encoded = true) encryptedAccountId: String,
        @Query(value = "beginIndex", encoded = true) beginIndex: Int,
        @Query(value = "endIndex", encoded = true) endIndex: Int
    ): Response<MatchListDTO>

    @GET("match/v4/matches/{matchId}")
    suspend fun getMatchByIdAsync(
        @Path(value = "matchId", encoded = true) matchId: Long
    ): Response<MatchDTO>

    @GET("spectator/v4/active-games/by-summoner/{encryptedSummonerId}")
    suspend fun getCurrentGameByEncryptedSummonerIdAsync(
        @Path(value = "encryptedSummonerId", encoded = true) encryptedSummonerId: String
    ): Response<CurrentGameInfoDTO>
}
