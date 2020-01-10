package com.github.profile.feature.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.lol4fun.core.base.BaseViewModel
import com.github.lol4fun.core.model.Customer
import com.github.profile.feature.business.ProfileBusiness
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.inject

class ProfileViewModel : BaseViewModel() {

    private val business: ProfileBusiness by inject()

    val onSuccessGetUserFirestore: MutableLiveData<Customer> = MutableLiveData()

    fun getUserFirestore() {
        viewModelScope.launch(Dispatchers.IO) {
            onSuccessGetUserFirestore.postValue(business.getUserFirestore())
        }
    }

}