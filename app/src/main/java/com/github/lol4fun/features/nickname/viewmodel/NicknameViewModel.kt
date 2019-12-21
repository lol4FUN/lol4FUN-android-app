package com.github.lol4fun.features.nickname.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.lol4fun.BaseViewModel
import com.github.lol4fun.R
import com.github.lol4fun.features.nickname.business.NicknameBusiness
import kotlinx.coroutines.launch

class NicknameViewModel : BaseViewModel() {
    
    private val business: NicknameBusiness by lazy { NicknameBusiness() }
    
    val onErrorSummonerNameLiveData: MutableLiveData<Int> = MutableLiveData()
    
    fun verifySummonerNameAndSave(summonerName: String) {
        if (business.isSummonerInvalid(summonerName)) {
            onErrorSummonerNameLiveData.postValue(R.string.nickname_error_blank_or_invalid)
        } else {
            business.saveSummonerNameAndIds(summonerName)
        }
    }

}