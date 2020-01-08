package com.github.lol4fun.core.api

import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_DDRAGON
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    val riotApi: RiotApi = getRiotApiClient().create(RiotApi::class.java)

    val dDragonRiotApi: DDragonRiotApi = getDDragonRiotApiClient().create(DDragonRiotApi::class.java)

    private fun getRiotApiClient() : Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getDDragonRiotApiClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_DDRAGON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}