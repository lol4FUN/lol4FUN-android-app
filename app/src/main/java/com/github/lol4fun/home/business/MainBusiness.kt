package com.github.lol4fun.home.business

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class MainBusiness {

    private val auth = FirebaseAuth.getInstance()

    fun userIsLogged(): Boolean {
        return auth.currentUser != null
    }

    fun getListOfProviders(): List<AuthUI.IdpConfig> {
        return listOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build()
        )
    }
}