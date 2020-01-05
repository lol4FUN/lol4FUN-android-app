package com.github.champions.di

import org.koin.core.context.loadKoinModules

object ChampionsDependencyInjection {
    private val loadChampionsModules by lazy {
        loadKoinModules(listOf(businessModule, repositoryModule, viewModelModule))
    }

    fun injectModules() = loadChampionsModules
}