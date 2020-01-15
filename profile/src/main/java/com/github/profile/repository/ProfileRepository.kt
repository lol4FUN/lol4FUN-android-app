package com.github.profile.repository

import com.github.lol4fun.core.base.BaseRepository
import com.github.lol4fun.util.ConstantsUtil
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.DocumentReference

class ProfileRepository : BaseRepository() {

    fun getUserReference(): DocumentReference {
        return db
            .collection(ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS)
            .document(auth.currentUser?.uid ?: "")
    }

    fun getAuthTask(profileUpdate: UserProfileChangeRequest): Task<Void>? {
        return auth.currentUser?.updateProfile(profileUpdate)
    }

}