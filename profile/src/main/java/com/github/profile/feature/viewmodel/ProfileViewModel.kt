package com.github.profile.feature.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.AuthUI
import com.github.lol4fun.core.base.BaseViewModel
import com.github.lol4fun.core.model.Customer
import com.github.profile.R
import com.github.profile.feature.business.ProfileBusiness
import com.github.profile.feature.listener.ProfileListener
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class ProfileViewModel : BaseViewModel(), ProfileListener {

    private val business: ProfileBusiness by inject{ parametersOf(this) }

    val onFailureSaveInfoData: MutableLiveData<Int> = MutableLiveData()
    val onSuccessSaveInfoData: MutableLiveData<Int> = MutableLiveData()

    val onSuccessGetUserFirestore: MutableLiveData<Customer> = MutableLiveData()

    fun getUserFirestore() {
        viewModelScope.launch(Dispatchers.IO) {
            onSuccessGetUserFirestore.postValue(business.getUserFirestore())
        }
    }

    fun saveUserInfo(hashMapProfile: HashMap<String, Any>) {
        viewModelScope.launch(Dispatchers.IO) {
            business.saveUserInfo(hashMapProfile)
        }
    }

    override fun onFailureSaveUserData() {
        onFailureSaveInfoData.postValue(R.string.error_saving_user_data)
    }

    override fun onSuccessSaveUserData() {
        onSuccessSaveInfoData.postValue(R.string.data_saved_successfully)
    }

    fun saveColorPreferenceFirestore(colorPreference: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            business.saveColorPreferenceFirestore(colorPreference)
        }
    }

    fun isAnonymous(): Boolean? {
        return business.isAnonymous()
    }

    fun getListOfProviders(): List<AuthUI.IdpConfig> {
        return business.getListOfProvidersWithoutAnonymous()
    }

    fun saveUserFirestore() {
        business.saveUserFirestore()
    }

    fun signInWithCredential(authCredential: AuthCredential) {
        business.signInWithCredential(authCredential)
    }

}