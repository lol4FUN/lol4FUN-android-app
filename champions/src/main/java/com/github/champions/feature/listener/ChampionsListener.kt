package com.github.champions.feature.listener

interface ChampionsListener {

    fun onErrorGetChampionsList(resourceId: Int)
    fun onSuccessGetChampionsList(championsMap: Map<String, Any>?)
}