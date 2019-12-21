package com.github.lol4fun.core.api

import com.github.lol4fun.util.ConstantsUtil.Api.HEADER_RIOT_TOKEN_NAME
import com.github.lol4fun.util.ConstantsUtil.Api.HEADER_RIOT_TOKEN_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader(HEADER_RIOT_TOKEN_NAME, HEADER_RIOT_TOKEN_VALUE)
                .build()
        )
    }
}