package com.github.champions.feature.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.champions.feature.business.ChampionsBusiness
import com.github.champions.feature.listener.ChampionsListener
import com.github.lol4fun.core.model.Champions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChampionsViewModel : ViewModel(), ChampionsListener {

    private val business: ChampionsBusiness by lazy { ChampionsBusiness(this) }

    val onErrorGetChampionsListLiveData: MutableLiveData<Int> = MutableLiveData()
    val onSuccessGetChampionsListLiveData: MutableLiveData<Map<String, Any>> = MutableLiveData()

    fun getChampions() {
        viewModelScope.launch(Dispatchers.IO) {
            business.getChampions()
        }
    }

    override fun onErrorGetChampionsList(resourceId: Int) {
        onErrorGetChampionsListLiveData.postValue(resourceId)
    }

    override fun onSuccessGetChampionsList(championsMap: Map<String, Any>?) {
        onSuccessGetChampionsListLiveData.postValue(championsMap)
    }
}