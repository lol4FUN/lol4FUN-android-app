package com.github.profile.feature.business

import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_NAME
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_SUMMONER_NAME
import com.github.profile.feature.listener.ProfileListener
import com.github.profile.repository.ProfileRepository
import com.google.android.gms.tasks.Task
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
        Tasks.whenAllComplete(getTaskToUpdateAuth(hashMapProfile), getTaskToUpdateFiretore(hashMapProfile))
            .addOnSuccessListener {
                profileListener.onSuccessSaveUserData()
            }.addOnFailureListener {
                profileListener.onFailureSaveUserData()
            }
    }

    private fun getTaskToUpdateFiretore(hashMapProfile: HashMap<String, Any>): Task<Void> {
        val userReference = repository.getUserReference()

        return userReference.update(
            mapOf(
                FIELD_USER_NAME to hashMapProfile[FIELD_USER_NAME],
                FIELD_USER_SUMMONER_NAME to hashMapProfile[FIELD_USER_SUMMONER_NAME],
                FIELD_USER_COLOR_PREFERENCE to hashMapProfile[FIELD_USER_COLOR_PREFERENCE]
            )
        )
    }

    private fun getTaskToUpdateAuth(hashMapProfile: HashMap<String, Any>): Task<Void>? {
        val profileUpdate = UserProfileChangeRequest.Builder()
            .setDisplayName(hashMapProfile[FIELD_USER_NAME] as? String)
            .build()

        return repository.getAuthTask(profileUpdate)
    }

    fun saveColorPreferenceFirestore(colorPreference: Long) {
        val userReference = repository.getUserReference()

        Tasks.await(
            userReference.update(
                FIELD_USER_COLOR_PREFERENCE, colorPreference
            )
        )
    }

}