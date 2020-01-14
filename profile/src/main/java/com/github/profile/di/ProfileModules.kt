package com.github.profile.di

import com.github.profile.feature.business.ProfileBusiness
import com.github.profile.feature.listener.ProfileListener
import com.github.profile.feature.viewmodel.ProfileViewModel
import com.github.profile.repository.ProfileRepository
import org.koin.dsl.module

val businessModule = module {
    single { (l: ProfileListener) -> ProfileBusiness(l) }
}

val repositoryModule = module {
    single { ProfileRepository() }
}

val viewModelModule = module {
    single { ProfileViewModel() }
}