package com.github.lol4fun.features.splash.business

import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.github.lol4fun.R
import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.repository.splash.SplashRepository
import org.koin.core.inject

class SplashBusiness : BaseBusiness() {

    private val repository: SplashRepository by inject()

    fun userIsLogged(): Boolean = repository.userIsLogged()

    fun getListOfProviders(): List<AuthUI.IdpConfig> {
        return listOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build()
        )
    }

    fun saveUserFirestore() {
        repository.saveUserFirestore()
    }

    fun getErrorLogin(errorCode: Int?): Int {
        return when(errorCode) {
            ErrorCodes.UNKNOWN_ERROR -> R.string.fui_error_unknown
            ErrorCodes.NO_NETWORK -> R.string.fui_no_internet
            ErrorCodes.ANONYMOUS_UPGRADE_MERGE_CONFLICT -> R.string.error_merge_anonymous_account
            ErrorCodes.ERROR_USER_DISABLED -> R.string.error_user_disabled
            ErrorCodes.EMAIL_MISMATCH_ERROR -> R.string.error_email_mismatch
            else -> R.string.fui_error_unknown
        }
    }
}