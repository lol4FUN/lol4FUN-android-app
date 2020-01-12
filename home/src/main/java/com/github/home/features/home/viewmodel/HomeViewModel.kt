package com.github.home.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.home.features.home.business.HomeBusiness
import com.github.home.features.home.business.HomeBusinessListener
import com.github.lol4fun.core.base.BaseViewModel
import com.github.lol4fun.core.model.CurrentGameInfo
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.core.model.MatchList
import com.github.lol4fun.core.model.MatchReference
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel: BaseViewModel(), HomeBusinessListener {
    private var _spinner = MutableLiveData<Boolean>()
    private var _alertMessage = MutableLiveData<String>()
    private var _history = MutableLiveData<List<Match>>()
    private var _currentGame = MutableLiveData<CurrentGameInfo>()
    private var _notInCurrentGame = MutableLiveData<Boolean>()

    private val business: HomeBusiness by inject { parametersOf(this) }

    val spinner: LiveData<Boolean>
        get() = _spinner
    val alertMessage: LiveData<String>
        get() = _alertMessage
    val history: LiveData<List<Match>>
        get() = _history
    val currentGame: LiveData<CurrentGameInfo>
        get() = _currentGame
    val notInCurrentGame: LiveData<Boolean>
        get() = _notInCurrentGame


    fun fetchHomeData() {
        _history.value = null
        _spinner.value = true
        viewModelScope.launch (coroutineContext.IO) {
            val callOne = async { business.getCurrentGame() }
            val callTwo = async { business.getHistory() }

            callOne.await()
            callTwo.await()
            _spinner.postValue(false)
        }
    }

    override fun onSuccessFetchCurrentGame(currentGame: CurrentGameInfo?, inCurrentGame: Boolean) {
        if (inCurrentGame) {
            _currentGame.postValue(currentGame)
        } else _notInCurrentGame.postValue(true)
    }

    override fun onSuccessFetchHistory(matches: MatchList) {
        loadDataParallel(matches.matches) {
            val match = it as MatchReference
            business.getDetailMatch(match.gameId)
        }
    }

    override fun onSuccessDetailMatch(match: Match) {
        val list = mutableListOf<Match>()
        list.addAll(_history.value ?: emptyList())
        list.add(match)
        _history.value = list
    }

    override fun onDefaultError(error: String?) {
        _spinner.value = false
        error?.let {
            _alertMessage.postValue(it)
        }
    }
}