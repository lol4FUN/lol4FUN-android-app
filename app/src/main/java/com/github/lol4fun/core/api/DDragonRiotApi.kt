package com.github.lol4fun.core.api

import com.github.lol4fun.core.model.Champions
import retrofit2.Response
import retrofit2.http.GET

interface DDragonRiotApi {

    @GET("9.24.2/data/pt_BR/champion.json")
    suspend fun getChampionsAsync(): Response<Champions>
}
