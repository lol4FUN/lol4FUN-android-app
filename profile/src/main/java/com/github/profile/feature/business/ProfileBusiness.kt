package com.github.profile.feature.business

import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.model.Customer
import com.github.profile.repository.ProfileRepository
import org.koin.core.inject

class ProfileBusiness : BaseBusiness() {

    private val repository: ProfileRepository by inject()

    fun getUserFirestore(): Customer? {
        return repository.getUserFirestore()
    }

}