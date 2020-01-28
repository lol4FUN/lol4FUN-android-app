package com.github.lol4fun.core.base

import com.firebase.ui.auth.AuthUI
import org.koin.core.KoinComponent

open class BaseBusiness : KoinComponent {

    fun getListOfProvidersWithoutAnonymous(): List<AuthUI.IdpConfig> {
        return listOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build()
        )
    }
}