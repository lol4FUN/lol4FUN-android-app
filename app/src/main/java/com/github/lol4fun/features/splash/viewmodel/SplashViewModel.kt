package com.github.lol4fun.features.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.AuthUI
import com.github.lol4fun.core.base.BaseViewModel
import com.github.lol4fun.features.splash.business.SplashBusiness
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.inject

class SplashViewModel : BaseViewModel() {
    private var _goToNickname = MutableLiveData<Boolean>()
    private var _goToHome = MutableLiveData<Boolean>()

    private val mainBusiness: SplashBusiness by inject()

    val goToNickname: LiveData<Boolean>
        get() = _goToNickname
    val goToHome: LiveData<Boolean>
        get() = _goToHome

    fun userIsLogged(): Boolean {

        return mainBusiness.userIsLogged()
    }

    fun getListOfProviders(): List<AuthUI.IdpConfig> {
        return mainBusiness.getListOfProviders()
    }

    fun saveUserFirestore() {
        mainBusiness.saveUserFirestore()
        viewModelScope.launch {
            delay(1000)
            _goToNickname.postValue(true)
        }
    }


    fun getErrorLogin(errorCode: Int?): Int {
        return mainBusiness.getErrorLogin(errorCode)
    }

    fun setMainNavigation() {
        viewModelScope.launch {
            delay(1000)
            _goToHome.postValue(true)
        }
    }
}