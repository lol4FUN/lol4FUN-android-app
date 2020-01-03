package com.github.profile.di

import org.koin.core.context.loadKoinModules

object ProfileDependencyInjection {
    private val loadHomeModules by lazy {
        loadKoinModules(listOf(businessModule, repositoryModule, viewModelModule))
    }

    fun injectModules() = loadHomeModules
}