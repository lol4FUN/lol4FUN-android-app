package com.github.lol4fun.features.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.AuthUI
import com.github.lol4fun.core.base.BaseViewModel
import com.github.lol4fun.features.splash.business.SplashBusiness
import com.github.lol4fun.features.splash.business.SplashListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class SplashViewModel : BaseViewModel(), SplashListener {
    private var _isDarkTheme = MutableLiveData<Boolean>()
    private var _goToNickname = MutableLiveData<Boolean>()
    private var _goToHome = MutableLiveData<Boolean>()
    private var _canCheckLogin = MutableLiveData<Boolean>()
    private var isCheckedDarkTheme = false

    private val business: SplashBusiness by inject { parametersOf(this) }

    val isThemeDark: LiveData<Boolean>
        get() = _isDarkTheme
    val goToNickname: LiveData<Boolean>
        get() = _goToNickname
    val goToHome: LiveData<Boolean>
        get() = _goToHome
    val canCheckLogin: LiveData<Boolean>
        get() = _canCheckLogin

    fun userIsLogged(): Boolean {
        return business.userIsLogged()
    }

    fun getListOfProviders(): List<AuthUI.IdpConfig> {
        return business.getListOfProvidersWithoutAnonymous()
    }

    fun saveUserFirestore() {
        business.saveUserFirestore()
        viewModelScope.launch {
            delay(1000)
            _goToNickname.postValue(true)
        }
    }

    override fun getPreferenceTheme(isDarkTheme: Boolean) {
        _isDarkTheme.value = isDarkTheme
        _isDarkTheme.value = null
    }


    fun getErrorLogin(errorCode: Int?): Int {
        return business.getErrorLogin(errorCode)
    }

    fun setMainNavigation() {
        viewModelScope.launch {
            delay(1000)
            _goToHome.postValue(true)
        }
    }

    fun setPreferenceTheme() {
        if (!isCheckedDarkTheme) {
            isCheckedDarkTheme = true
            business.getPreferencesTheme()
        } else _canCheckLogin.postValue(true)
    }
}