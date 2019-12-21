package com.github.lol4fun.nickname.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lol4fun.R
import com.github.lol4fun.nickname.business.NicknameBusiness

class NicknameViewModel : ViewModel() {
    
    private val business: NicknameBusiness by lazy { NicknameBusiness() }
    
    val onErrorSummonerNameLiveData: MutableLiveData<Int> = MutableLiveData()
    
    fun verifySummonerNameAndSave(summonerName: String) {
        if (business.isSummonerInvalid(summonerName)) {
            onErrorSummonerNameLiveData.postValue(R.string.nickname_error_blank_or_invalid)
        }
    }

}