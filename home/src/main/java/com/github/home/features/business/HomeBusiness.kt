package com.github.home.features.business

import com.github.home.repository.HomeRepository
import com.github.lol4fun.core.base.BaseBusiness
import org.koin.core.inject


class HomeBusiness(private val listener: HomeBusinessListener): BaseBusiness() {

    private val repository: HomeRepository by inject()

    suspend fun getHistory() {
        //TODO
    }
}