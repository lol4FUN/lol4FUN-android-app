package com.github.home.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.home.features.home.business.HomeBusiness
import com.github.home.features.home.business.HomeBusinessListener
import com.github.lol4fun.core.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel: BaseViewModel(), HomeBusinessListener {
    private var _spinner = MutableLiveData<Boolean>()
    private var _alertMessage = MutableLiveData<String>()

    private val business: HomeBusiness by inject { parametersOf(this) }

    val spinner: LiveData<Boolean>
        get() = _spinner
    val alertMessage: LiveData<String>
        get() = _alertMessage


    fun fetchPlayerHistory() {
        _spinner.value = true
        viewModelScope.launch(coroutineContext.IO) {
            business.getHistory()
        }

    }

    override fun onSuccessFetchHistory() {
        _spinner.value = false
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorFetchHistory(error: String?) {
        _spinner.value = false
        error?.let {
            _alertMessage.postValue(it)
        }
    }
}