package com.github.lol4fun.features.home.business

import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes.*
import com.github.lol4fun.R
import com.github.lol4fun.core.repository.home.MainRepository
import com.google.firebase.auth.FirebaseAuth

class MainBusiness {

    private val auth = FirebaseAuth.getInstance()
    private val mainRepository = MainRepository()

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

    fun saveUserFirestore() {
        mainRepository.saveUserFirestore()
    }

    fun getErrorLogin(errorCode: Int?): Int {
        return when(errorCode) {
            UNKNOWN_ERROR -> R.string.fui_error_unknown
            NO_NETWORK -> R.string.fui_no_internet
            PLAY_SERVICES_UPDATE_CANCELLED,
            PROVIDER_ERROR,
            DEVELOPER_ERROR -> R.string.fui_error_unknown
            ANONYMOUS_UPGRADE_MERGE_CONFLICT -> R.string.error_merge_anonymous_account
            ERROR_USER_DISABLED -> R.string.error_user_disabled
            EMAIL_MISMATCH_ERROR -> R.string.error_email_mismatch
            else -> R.string.fui_error_unknown
        }
    }
}