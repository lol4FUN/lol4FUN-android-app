package com.github.profile.di

import org.koin.core.context.loadKoinModules

object ProfileDependencyInjection {
    private val loadProfileModules by lazy {
        loadKoinModules(listOf(businessModule, repositoryModule, viewModelModule))
    }

    fun injectModules() = loadProfileModules
}