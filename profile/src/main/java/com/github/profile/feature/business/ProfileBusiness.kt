package com.github.profile.feature.business

import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_NAME
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_SUMMONER_NAME
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS
import com.github.profile.feature.listener.ProfileListener
import com.github.profile.repository.ProfileRepository
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.UserProfileChangeRequest
import org.koin.core.inject

class ProfileBusiness(
    private val profileListener: ProfileListener
) : BaseBusiness() {

    private val repository: ProfileRepository by inject()

    fun getUserFirestore(): Customer? {
        return repository.getUserFirestore()
    }

    fun saveUserInfo(hashMapProfile: HashMap<String, Any>) {
        val userReference = repository.db
            .collection(DATABASE_CUSTOMERS)
            .document(repository.auth.currentUser?.uid ?: "")

        val taskUpdateDb = userReference.update(
            mapOf(
                FIELD_USER_NAME to hashMapProfile[FIELD_USER_NAME],
                FIELD_USER_SUMMONER_NAME to hashMapProfile[FIELD_USER_SUMMONER_NAME],
                FIELD_USER_COLOR_PREFERENCE to hashMapProfile[FIELD_USER_COLOR_PREFERENCE]
            )
        )

        val profileUpdate = UserProfileChangeRequest.Builder()
            .setDisplayName(hashMapProfile[FIELD_USER_NAME] as? String)
            .build()

        val taskUpdateAuth = repository.auth.currentUser?.updateProfile(profileUpdate)

        Tasks.whenAllComplete(taskUpdateAuth, taskUpdateDb)
            .addOnSuccessListener {
                profileListener.onSuccessSaveUserData()
            }.addOnFailureListener {
                profileListener.onFailureSaveUserData()
            }
    }

}