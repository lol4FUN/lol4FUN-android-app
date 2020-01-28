package com.github.lol4fun.core.repository.splash

import com.github.lol4fun.core.base.BaseRepository
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.util.ConstantsUtil
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_DARK
import com.google.firebase.firestore.Source
import java.util.*

class SplashRepository : BaseRepository() {

    fun userIsLogged(): Boolean {
        return auth.currentUser != null
    }

    fun getPreferenceTheme(onResponse: (Boolean) -> Unit, onError: () -> Unit) {
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
                onResponse(customer?.colorPreference == FIELD_USER_COLOR_PREFERENCE_DARK)
            } else onError()
        }
    }
}