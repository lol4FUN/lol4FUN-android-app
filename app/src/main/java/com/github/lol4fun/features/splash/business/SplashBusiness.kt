package com.github.lol4fun.features.splash.business

import com.firebase.ui.auth.ErrorCodes
import com.github.lol4fun.R
import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.repository.splash.SplashRepository
import org.koin.core.inject

class SplashBusiness(private val listener: SplashListener) : BaseBusiness() {

    private val repository: SplashRepository by inject()

    fun userIsLogged(): Boolean = repository.userIsLogged()

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

    fun getPreferencesTheme() {
        repository.getPreferenceTheme(::onResponsePreferenceTheme, ::onErrorResponsePreferenceTheme)
    }

    private fun onResponsePreferenceTheme(isDarkTheme: Boolean) {
        listener.getPreferenceTheme(isDarkTheme)
    }

    private fun onErrorResponsePreferenceTheme() {
        listener.getPreferenceTheme(null)
    }
}