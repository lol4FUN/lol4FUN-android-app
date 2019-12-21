package com.github.lol4fun.core.repository.home

import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.core.repository.BaseRepository
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_DARK
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_EMAIL
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_NAME
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_PREMIUM
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainRepository : BaseRepository() {

    fun saveUserFirestore() {
        val currentUser = auth.currentUser

        val newUser = hashMapOf(
            FIELD_USER_NAME to currentUser?.displayName,
            FIELD_USER_EMAIL to currentUser?.email,
            FIELD_USER_PREMIUM to false,
            FIELD_USER_COLOR_PREFERENCE to FIELD_USER_COLOR_PREFERENCE_DARK
        )

        db.collection(
            DATABASE_CUSTOMERS
        ).document(
            auth.currentUser?.uid ?: UUID.randomUUID().toString()
        ).set(
            newUser
        )
    }

    fun getUserFirestore(): Customer? {
        val docRef = FirebaseFirestore.getInstance()
            .collection(DATABASE_CUSTOMERS)
            .document(FirebaseAuth.getInstance().currentUser?.uid ?: "")

        val result = Tasks.await(docRef.get())

        return result.toObject(Customer::class.java)
    }
}