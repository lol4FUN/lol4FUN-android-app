package com.github.champions.feature.business

import com.github.champions.R
import com.github.champions.feature.listener.ChampionsListener
import com.github.champions.repository.ChampionsRepository
import com.github.lol4fun.core.api.Status
import com.github.lol4fun.core.model.Champions

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
                championsListener.onSuccessGetChampionsList(resultGetChampions.data as? Champions)
            }
        }
    }

}