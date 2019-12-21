package com.github.lol4fun.nickname.business

class NicknameBusiness  {

    fun isSummonerInvalid(summonerName: String): Boolean {
        return summonerName.isBlank()
    }

}