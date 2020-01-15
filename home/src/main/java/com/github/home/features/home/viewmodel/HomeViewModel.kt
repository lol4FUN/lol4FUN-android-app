package com.github.home.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.home.features.home.business.HomeBusiness
import com.github.home.features.home.business.HomeBusinessListener
import com.github.home.repository.HistoryDataSource
import com.github.lol4fun.core.base.BaseViewModel
import com.github.lol4fun.core.model.CurrentGameInfo
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.util.ConstantsUtil.Home.PAGE_SIZE
import kotlinx.coroutines.launch
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel: BaseViewModel(), HomeBusinessListener {
    private var _spinner = MutableLiveData<Boolean>()
    private var _alertMessage = MutableLiveData<String>()
    private var _currentGame = MutableLiveData<CurrentGameInfo>()
    private var _notInCurrentGame = MutableLiveData<Boolean>()

    private val business: HomeBusiness by inject { parametersOf(this) }

    val spinner: LiveData<Boolean>
        get() = _spinner
    val alertMessage: LiveData<String>
        get() = _alertMessage
    val currentGame: LiveData<CurrentGameInfo>
        get() = _currentGame
    val notInCurrentGame: LiveData<Boolean>
        get() = _notInCurrentGame
    var history: LiveData<PagedList<Match>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        history = initializedPagedListBuilder(config).build()
    }

    fun fetchHomeData() {
        _spinner.postValue(true)
        viewModelScope.launch (coroutineContext.IO) {
            business.getCurrentGame()
        }
    }

    override fun onSuccessFetchCurrentGame(currentGame: CurrentGameInfo?, inCurrentGame: Boolean) {
        _spinner.postValue(false)

        if (inCurrentGame) {
            _currentGame.postValue(currentGame)
        } else _notInCurrentGame.postValue(true)
    }

    override fun onDefaultError(error: String?) {
        _spinner.postValue(false)
        error?.let {
            _alertMessage.postValue(it)
        }
    }

    private fun initializedPagedListBuilder(
        config: PagedList.Config
    ): LivePagedListBuilder<Long, Match> {
        val dataSourceFactory = object : DataSource.Factory<Long, Match>() {
            override fun create(): DataSource<Long, Match> {
                return HistoryDataSource(coroutineContext.IO)
            }
        }

        return LivePagedListBuilder<Long, Match>(dataSourceFactory, config)
    }
}