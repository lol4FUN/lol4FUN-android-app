package com.github.home.features.home.business

import com.github.lol4fun.core.model.CurrentGameInfo
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.core.model.MatchList

interface HomeBusinessListener {

    fun onSuccessDetailMatch(match: Match)

    fun onSuccessFetchCurrentGame(currentGame: CurrentGameInfo?, inCurrentGame: Boolean)

    fun onSuccessFetchHistory(matches: MatchList)

    fun onDefaultError(error: String?)
}