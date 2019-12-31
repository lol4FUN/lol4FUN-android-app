package com.github.lol4fun.di

import com.github.lol4fun.core.repository.home.MainRepository
import com.github.lol4fun.core.repository.nickname.NicknameRepository
import com.github.lol4fun.features.home.business.MainBusiness
import com.github.lol4fun.features.home.viewmodel.MainViewModel
import com.github.lol4fun.features.nickname.business.NicknameBusiness
import com.github.lol4fun.features.nickname.listener.NicknameListener
import com.github.lol4fun.features.nickname.viewmodel.NicknameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {
    single { MainBusiness() }
    single { (l: NicknameListener) -> NicknameBusiness(l) }
}

val repositoryModule = module {
    single { MainRepository() }
    single { NicknameRepository() }
}

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { NicknameViewModel() }
}