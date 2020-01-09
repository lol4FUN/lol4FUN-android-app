package com.github.home.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.home.features.home.business.HomeBusiness
import com.github.home.features.home.business.HomeBusinessListener
import com.github.lol4fun.core.base.BaseViewModel
import com.github.lol4fun.core.model.MatchList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel: BaseViewModel(), HomeBusinessListener {
    private var _spinner = MutableLiveData<Boolean>()
    private var _alertMessage = MutableLiveData<String>()
    private var _history = MutableLiveData<MatchList>()

    private val business: HomeBusiness by inject { parametersOf(this) }

    val spinner: LiveData<Boolean>
        get() = _spinner
    val alertMessage: LiveData<String>
        get() = _alertMessage
    val history: LiveData<MatchList>
        get() = _history


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

    override fun onSuccessFetchMatch() {
        //TODO()
    }

    override fun onSuccessFetchHistory(matches: MatchList) {
        _history.postValue(matches)
    }

    override fun onErrorFetchMatch(error: String?) {
        setError(error)
    }

    override fun onErrorFetchHistory(error: String?) {
       setError(error)
    }

    private fun setError(error: String?) {
        _spinner.value = false
        error?.let {
            _alertMessage.postValue(it)
        }
    }
}