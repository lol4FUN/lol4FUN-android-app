package com.github.lol4fun.core.api

import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    val riotApi: RiotApi = getRiotApiClient().create(RiotApi::class.java)

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
}