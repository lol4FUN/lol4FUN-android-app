package com.github.search.di

import org.koin.core.context.loadKoinModules

object SearchDependencyInjection {
    private val loadSearchModules by lazy {
        loadKoinModules(listOf(businessModule, repositoryModule, viewModelModule))
    }

    fun injectModules() = loadSearchModules
}