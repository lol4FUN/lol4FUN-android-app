package com.github.lol4fun.features.splash.business

import com.firebase.ui.auth.ErrorCodes
import com.github.lol4fun.R
import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.core.repository.splash.SplashRepository
import com.github.lol4fun.util.ConstantsUtil
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_DARK
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import org.koin.core.inject
import java.util.*

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
        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        val docRef = db
            .collection(ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS)
            .document(auth.currentUser?.uid ?: UUID.randomUUID().toString())

        // Source can be CACHE, SERVER, or DEFAULT.
        val source = Source.CACHE

        // Get the document, forcing the SDK to use the offline cache
        docRef.get(source).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Document found in the offline cache
                val customer = task.result?.toObject(Customer::class.java)
                listener.getPreferenceTheme(
                    customer?.colorPreference == FIELD_USER_COLOR_PREFERENCE_DARK
                )
            }
        }
    }
}