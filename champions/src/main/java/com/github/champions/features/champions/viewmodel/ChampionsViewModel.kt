package com.github.champions.features.champions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.champions.features.champions.business.ChampionsBusiness
import com.github.champions.features.champions.listener.ChampionsListener
import com.github.lol4fun.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class ChampionsViewModel : BaseViewModel(), ChampionsListener {

    private val business: ChampionsBusiness by inject { parametersOf(this) }

    val onErrorGetChampionsListLiveData: MutableLiveData<Int> = MutableLiveData()
    val onSuccessGetChampionsListLiveData: MutableLiveData<ArrayList<Any>> = MutableLiveData()

    fun getChampions() {
        viewModelScope.launch(Dispatchers.IO) {
            business.getChampions()
        }
    }

    override fun onErrorGetChampionsList(resourceId: Int) {
        onErrorGetChampionsListLiveData.postValue(resourceId)
    }

    override fun onSuccessGetChampionsList(championsList: ArrayList<Any>?) {
        onSuccessGetChampionsListLiveData.postValue(championsList)
    }
}