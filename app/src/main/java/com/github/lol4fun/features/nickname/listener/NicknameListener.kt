package com.github.lol4fun.features.nickname.listener

interface NicknameListener {

    fun onErrorGetSummonerNameApi(message: String)
    fun onErrorSaveSummonerInfoFirestore(message: String)
    fun onSuccessSaveSummonerInfo()
}