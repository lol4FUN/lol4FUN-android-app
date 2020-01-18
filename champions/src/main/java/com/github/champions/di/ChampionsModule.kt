package com.github.champions.di

import com.github.champions.features.champions.business.ChampionsBusiness
import com.github.champions.features.champions.listener.ChampionsListener
import com.github.champions.features.champions.viewmodel.ChampionsViewModel
import com.github.champions.repository.ChampionsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {
    single { (l: ChampionsListener) -> ChampionsBusiness(l) }
}

val repositoryModule = module {
    single { ChampionsRepository() }
}

val viewModelModule = module {
    viewModel { ChampionsViewModel() }
}