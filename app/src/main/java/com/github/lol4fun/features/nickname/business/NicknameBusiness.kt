package com.github.lol4fun.features.nickname.business

import com.github.lol4fun.BaseBusiness
import com.github.lol4fun.core.api.Status
import com.github.lol4fun.core.model.SummonerInfo
import com.github.lol4fun.core.repository.nickname.NicknameRepository
import kotlinx.coroutines.launch

class NicknameBusiness : BaseBusiness() {

    private val nicknameRepository = NicknameRepository()

    fun isSummonerInvalid(summonerName: String): Boolean {
        return summonerName.isBlank()
    }

    fun saveSummonerNameAndIds(summonerName: String) {
        scope.launch {
            val resource = nicknameRepository.getSummonerNameByNameAtRiotApi(summonerName)

            when (resource.status) {
                Status.ERROR -> {
                    //todo handle error
                }
                Status.SUCCESS -> {
                    val summonerInfo = resource.data as? SummonerInfo

                    summonerInfo?.let {
                        nicknameRepository.saveSummonerInfoAtFirestore(it)
                    }
                }
            }
        }
    }

}