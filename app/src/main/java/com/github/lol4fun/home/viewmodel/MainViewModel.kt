package com.github.lol4fun.home.viewmodel

import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.AuthUI
import com.github.lol4fun.home.business.MainBusiness

class MainViewModel : ViewModel() {

    private val mainBusiness: MainBusiness by lazy { MainBusiness() }

    fun userIsLogged(): Boolean {
        return mainBusiness.userIsLogged()
    }

    fun getListOfProviders(): List<AuthUI.IdpConfig> {
        return mainBusiness.getListOfProviders()
    }

    fun saveUserFirestore() {
        mainBusiness.saveUserFirestore()
    }
}