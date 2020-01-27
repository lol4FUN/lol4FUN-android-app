package com.github.lol4fun.features.main.business

import com.firebase.ui.auth.ErrorCodes.*
import com.github.lol4fun.R
import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.repository.main.MainRepository
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.inject

class MainBusiness: BaseBusiness() {

    private val auth = FirebaseAuth.getInstance()
    private val mainRepository: MainRepository by inject()

    fun userIsLogged(): Boolean {
        return auth.currentUser != null
    }

    fun saveUserFirestore() {
        mainRepository.saveUserFirestore()
    }

    fun getErrorLogin(errorCode: Int?): Int {
        return when(errorCode) {
            UNKNOWN_ERROR -> R.string.fui_error_unknown
            NO_NETWORK -> R.string.fui_no_internet
            ANONYMOUS_UPGRADE_MERGE_CONFLICT -> R.string.error_merge_anonymous_account
            ERROR_USER_DISABLED -> R.string.error_user_disabled
            EMAIL_MISMATCH_ERROR -> R.string.error_email_mismatch
            else -> R.string.fui_error_unknown
        }
    }
}