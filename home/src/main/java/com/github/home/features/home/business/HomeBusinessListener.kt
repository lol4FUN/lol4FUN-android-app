package com.github.home.features.home.business

import com.github.lol4fun.core.model.CurrentGameInfo

interface HomeBusinessListener {

    fun onCurrentGameStatus(currentGame: CurrentGameInfo?, inCurrentGame: Boolean)

    fun onDefaultError(error: String?)
}