package com.github.lol4fun.features.nickname.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.lol4fun.BaseViewModel
import com.github.lol4fun.R
import com.github.lol4fun.features.nickname.business.NicknameBusiness
import com.github.lol4fun.features.nickname.listener.NicknameListener

class NicknameViewModel : BaseViewModel(), NicknameListener {

    private val business: NicknameBusiness by lazy { NicknameBusiness(this) }
    
    val onErrorSummonerNameLiveData: MutableLiveData<Int> = MutableLiveData()
    val onErrorGetSummonerNameApiLiveData: MutableLiveData<String> = MutableLiveData()
    val onErrorSaveSummonerInfoFirestoreLiveData: MutableLiveData<String> = MutableLiveData()
    val onSuccessSaveSummonerInfoLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun verifySummonerNameAndSave(summonerName: String) {
        if (business.isSummonerInvalid(summonerName)) {
            onErrorSummonerNameLiveData.postValue(R.string.nickname_error_blank_or_invalid)
        } else {
            business.saveSummonerNameAndIds(summonerName)
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