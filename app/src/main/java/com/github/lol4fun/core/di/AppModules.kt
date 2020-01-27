package com.github.lol4fun.core.di

import com.github.lol4fun.core.repository.splash.SplashRepository
import com.github.lol4fun.core.repository.nickname.NicknameRepository
import com.github.lol4fun.features.nickname.business.NicknameBusiness
import com.github.lol4fun.features.nickname.listener.NicknameListener
import com.github.lol4fun.features.nickname.viewmodel.NicknameViewModel
import com.github.lol4fun.features.splash.business.SplashBusiness
import com.github.lol4fun.features.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {
    single { SplashBusiness() }
    single { (l: NicknameListener) -> NicknameBusiness(l) }
}

val repositoryModule = module {
    single { SplashRepository() }
    single { NicknameRepository() }
}

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { NicknameViewModel() }
}