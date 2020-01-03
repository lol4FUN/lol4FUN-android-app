package com.github.search.di

import org.koin.core.context.loadKoinModules

object SearchDependencyInjection {
    private val loadHomeModules by lazy {
        loadKoinModules(listOf(businessModule, repositoryModule, viewModelModule))
    }

    fun injectModules() = loadHomeModules
}