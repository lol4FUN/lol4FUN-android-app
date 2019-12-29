package com.github.lol4fun.core.api

import com.github.lol4fun.core.model.SummonerInfo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApi {

    @GET("summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummonerNameByNameAsync(
        @Path(value = "summonerName", encoded = true) summonerName: String
    ): Response<SummonerInfo>
}
