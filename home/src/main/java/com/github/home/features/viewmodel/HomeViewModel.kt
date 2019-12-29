package com.github.home.features.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.home.features.business.HomeBusiness
import com.github.home.features.business.HomeBusinessListener
import com.github.lol4fun.base.BaseViewModel

class HomeViewModel: BaseViewModel(), HomeBusinessListener {
    private var _spinner = MutableLiveData<Boolean>()
    private var _alertMessage = MutableLiveData<String>()

    private val business: HomeBusiness by lazy { HomeBusiness(this) }

    val spinner: LiveData<Boolean>
        get() = _spinner
    val alertMessage: LiveData<String>
        get() = _alertMessage


    fun fetchPlayerHistory() {
        launchDataLoad(_spinner) {
            business.getHistory()
        }
    }

    override fun onSuccessFetchHistory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorFetchHistory(error: String?) {
        error?.let {
            _alertMessage.value = it
        }
    }
}