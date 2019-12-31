package com.github.lol4fun.features.home.viewmodel

import com.firebase.ui.auth.AuthUI
import com.github.lol4fun.base.BaseViewModel
import com.github.lol4fun.features.home.business.MainBusiness
import org.koin.core.inject

class MainViewModel : BaseViewModel() {

    private val mainBusiness: MainBusiness by inject()

    fun userIsLogged(): Boolean {
        return mainBusiness.userIsLogged()
    }

    fun getListOfProviders(): List<AuthUI.IdpConfig> {
        return mainBusiness.getListOfProviders()
    }

    fun saveUserFirestore() {
        mainBusiness.saveUserFirestore()
    }

    fun getErrorLogin(errorCode: Int?): Int {
        return mainBusiness.getErrorLogin(errorCode)
    }
}