package com.github.home.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.home.features.home.business.HomeBusiness
import com.github.home.features.home.business.HomeBusinessListener
import com.github.lol4fun.core.base.BaseViewModel
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
    private var _history = MutableLiveData<MatchList>()
    private var _detailMatch = MutableLiveData<Match>()

    private val business: HomeBusiness by inject { parametersOf(this) }

    val spinner: LiveData<Boolean>
        get() = _spinner
    val alertMessage: LiveData<String>
        get() = _alertMessage
    val history: LiveData<MatchList>
        get() = _history
    val detailMatch: LiveData<Match>
        get() = _detailMatch


    fun fetchHomeData() {
        _spinner.value = true
        viewModelScope.launch (coroutineContext.IO) {
            val callOne = async { business.getActualMatch() }
            val callTwo = async { business.getHistory() }

            callOne.await()
            callTwo.await()
            _spinner.postValue(false)
        }
    }

    override fun onSuccessFetchCurrentMatch() {
        //TODO()
    }

    override fun onSuccessFetchHistory(matches: MatchList) {
        _history.postValue(matches)
        loadDataParallel(matches.matches) {
            val match = it as MatchReference
            business.getDetailMatch(match.gameId)
        }
    }

    override fun onSuccessDetailMatch(match: Match) {
        _detailMatch.value = match
    }

    override fun onDefaultError(error: String?) {
        _spinner.value = false
        error?.let {
            _alertMessage.postValue(it)
        }
    }
}