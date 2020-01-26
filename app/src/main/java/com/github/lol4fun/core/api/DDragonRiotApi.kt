package com.github.lol4fun.core.api

import com.github.lol4fun.core.model.Champions
import com.github.lol4fun.core.model.Items
import retrofit2.Response
import retrofit2.http.GET

interface DDragonRiotApi {

    @GET("data/pt_BR/champion.json")
    suspend fun getChampionsAsync(): Response<Champions>

    @GET("data/pt_BR/item.json")
    suspend fun getItemsAsync(): Response<Items>
}
