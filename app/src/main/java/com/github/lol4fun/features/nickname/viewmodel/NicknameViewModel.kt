package com.github.lol4fun.features.nickname.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.lol4fun.R
import com.github.lol4fun.core.base.BaseViewModel
import com.github.lol4fun.features.nickname.business.NicknameBusiness
import com.github.lol4fun.features.nickname.listener.NicknameListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class NicknameViewModel : BaseViewModel(), NicknameListener {

    private val business: NicknameBusiness by inject { parametersOf(this) }

    val onErrorSummonerNameLiveData: MutableLiveData<Int> = MutableLiveData()
    val onErrorGetSummonerNameApiLiveData: MutableLiveData<String> = MutableLiveData()
    val onErrorSaveSummonerInfoFirestoreLiveData: MutableLiveData<String> = MutableLiveData()
    val onSuccessSaveSummonerInfoLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun verifySummonerNameAndSave(summonerName: String) {
        if (business.isSummonerInvalid(summonerName)) {
            onErrorSummonerNameLiveData.postValue(R.string.nickname_error_blank_or_invalid)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                business.saveSummonerNameAndIds(summonerName)
            }
        }
    }

    override fun onErrorGetSummonerNameApi(message: String) {
        onErrorGetSummonerNameApiLiveData.postValue(message)
    }

    override fun onErrorSaveSummonerInfoFirestore(message: String) {
        onErrorSaveSummonerInfoFirestoreLiveData.postValue(message)
    }

    override fun onSuccessSaveSummonerInfo() {
        onSuccessSaveSummonerInfoLiveData.postValue(true)
    }
}