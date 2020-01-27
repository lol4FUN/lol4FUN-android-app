package com.github.lol4fun.core.repository.splash

import com.github.lol4fun.core.base.BaseRepository
import com.github.lol4fun.util.ConstantsUtil
import java.util.*

class SplashRepository : BaseRepository() {

    fun userIsLogged(): Boolean {
        return auth.currentUser != null
    }

    fun saveUserFirestore() {
        val currentUser = auth.currentUser

        val newUser = hashMapOf(
            ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_NAME to currentUser?.displayName,
            ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_EMAIL to currentUser?.email,
            ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_PREMIUM to false,
            ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE to ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_DARK
        )

        db.collection(
            ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS
        ).document(
            auth.currentUser?.uid ?: UUID.randomUUID().toString()
        ).set(
            newUser
        )
    }
}