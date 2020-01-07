package com.github.champions.repository

import com.github.lol4fun.core.api.Resource
import com.github.lol4fun.core.base.BaseRepository

class ChampionsRepository : BaseRepository() {

    suspend fun getChampions(): Resource {
        return safeApiCall(
            call = { apiDDragon.getChampionsAsync() }
        )
    }


}