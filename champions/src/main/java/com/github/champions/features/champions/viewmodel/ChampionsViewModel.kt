package com.github.champions.features.champions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.champions.features.champions.business.ChampionsBusiness
import com.github.champions.features.champions.listener.ChampionsListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChampionsViewModel : ViewModel(), ChampionsListener {

    private val business: ChampionsBusiness by lazy { ChampionsBusiness(this) }

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