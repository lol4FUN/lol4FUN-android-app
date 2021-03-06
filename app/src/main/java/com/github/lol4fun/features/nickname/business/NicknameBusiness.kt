package com.github.lol4fun.features.nickname.business

import com.github.lol4fun.core.api.Status
import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.model.SummonerInfo
import com.github.lol4fun.core.repository.nickname.NicknameRepository
import com.github.lol4fun.features.nickname.listener.NicknameListener
import com.github.lol4fun.util.ConstantsUtil.Error.ERROR_DEFAULT
import org.koin.core.inject

class NicknameBusiness(
    private val nicknameListener: NicknameListener
) : BaseBusiness() {

    private val nicknameRepository: NicknameRepository by inject()

    fun isSummonerInvalid(summonerName: String): Boolean {
        return summonerName.isBlank()
    }

    suspend fun saveSummonerNameAndIds(summonerName: String) {
        val resultApiRiot = nicknameRepository.getSummonerNameByNameAtRiotApi(summonerName)

        when (resultApiRiot.status) {
            Status.ERROR -> {
                nicknameListener.onErrorGetSummonerNameApi(
                    message = resultApiRiot.message ?: ERROR_DEFAULT
                )
            }
            Status.SUCCESS -> {
                val summonerInfo = resultApiRiot.data as? SummonerInfo

                summonerInfo?.let {
                    val resultFirestore = nicknameRepository.saveSummonerInfoAtFirestore(it)

                    when (resultFirestore.status) {
                        Status.SUCCESS -> {
                            nicknameListener.onSuccessSaveSummonerInfo()
                        }
                        Status.ERROR -> {
                            nicknameListener.onErrorSaveSummonerInfoFirestore(
                                message = resultFirestore.message ?: ERROR_DEFAULT
                            )
                        }
                    }
                }
            }
        }
    }
}