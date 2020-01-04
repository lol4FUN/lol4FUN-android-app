package com.github.champions.features.champions.business

import com.github.champions.R
import com.github.champions.features.champions.listener.ChampionsListener
import com.github.champions.repository.ChampionsRepository
import com.github.lol4fun.core.api.Status
import com.github.lol4fun.core.model.Champions
import com.github.lol4fun.extensions.serializeToMap

class ChampionsBusiness(
    private val championsListener: ChampionsListener
) {

    private val repository: ChampionsRepository by lazy { ChampionsRepository() }

    suspend fun getChampions() {
        val resultGetChampions = repository.getChampions()

        when(resultGetChampions.status) {
            Status.ERROR -> {
                championsListener.onErrorGetChampionsList(R.string.error_get_champions_list)
            }
            Status.SUCCESS -> {
                val championsMap = (resultGetChampions.data as? Champions)?.data.serializeToMap()

                val championsList = arrayListOf<Any>()

                championsMap.forEach {
                    championsList.add(it.value)
                }

                championsListener.onSuccessGetChampionsList(championsList)
            }
        }
    }

}