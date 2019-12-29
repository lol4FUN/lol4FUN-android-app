package com.github.champions.feature.listener

import com.github.lol4fun.core.model.Champions

interface ChampionsListener {

    fun onErrorGetChampionsList(resourceId: Int)
    fun onSuccessGetChampionsList(champions: Champions?)
}