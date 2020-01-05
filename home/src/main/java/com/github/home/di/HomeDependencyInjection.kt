package com.github.home.di

import org.koin.core.context.loadKoinModules

object HomeDependencyInjection {
    private val loadHomeModules by lazy {
        loadKoinModules(listOf(businessModule, repositoryModule, viewModelModule))
    }

    fun injectModules() = loadHomeModules
}