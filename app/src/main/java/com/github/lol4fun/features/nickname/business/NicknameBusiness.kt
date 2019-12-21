package com.github.lol4fun.features.nickname.business

import com.github.lol4fun.core.repository.nickname.NicknameRepository

class NicknameBusiness  {

    private val nicknameRepository = NicknameRepository()

    fun isSummonerInvalid(summonerName: String): Boolean {
        return summonerName.isBlank()
    }

    fun saveSummonerNameAndIds(summonerName: String) {
        nicknameRepository.saveSummonerNameAndIds(summonerName)
    }

}