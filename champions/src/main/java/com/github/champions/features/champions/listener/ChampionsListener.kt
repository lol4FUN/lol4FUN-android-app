package com.github.champions.features.champions.listener

interface ChampionsListener {

    fun onErrorGetChampionsList(resourceId: Int)
    fun onSuccessGetChampionsList(championsList: ArrayList<Any>?)
}