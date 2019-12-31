package com.github.home.di

import com.github.home.features.business.HomeBusiness
import com.github.home.features.business.HomeBusinessListener
import com.github.home.features.viewmodel.HomeViewModel
import com.github.home.repository.HomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {
    single { (l: HomeBusinessListener) -> HomeBusiness(l) }
}

val repositoryModule = module {
    single { HomeRepository() }
}

val viewModelModule = module {
    viewModel { HomeViewModel() }
}