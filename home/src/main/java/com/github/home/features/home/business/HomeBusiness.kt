package com.github.home.features.home.business

import com.github.home.repository.HomeRepository
import com.github.lol4fun.core.api.Status
import com.github.lol4fun.core.base.BaseBusiness
import com.github.lol4fun.core.model.*
import com.github.lol4fun.core.model.dto.MatchDTO
import com.github.lol4fun.core.model.dto.MatchListDTO
import com.github.lol4fun.core.model.dto.toMatch
import com.github.lol4fun.core.model.dto.toMatchList
import com.github.lol4fun.extensions.*
import com.github.lol4fun.util.ConstantsUtil.Error.ERROR_DEFAULT
import org.koin.core.inject


class HomeBusiness(private val listener: HomeBusinessListener?) : BaseBusiness() {
    private var champions: List<Champion>? = null
    private var items: List<Item>? = null
    private var accountId = ""
    private val repository: HomeRepository by inject()

    fun getUserFirestore(): Customer? {
        return repository.getUserFirestore()
    }

    suspend fun getCurrentGame(encryptedSummonerId: String) {
        val result = repository.getCurrentGameInfoById(encryptedSummonerId)

        when (result.status) {
            Status.ERROR -> {
                if (result.message == "Data not found") {
                    listener?.onCurrentGameStatus(null, false)
                } else listener?.onDefaultError(error = result.message ?: ERROR_DEFAULT)
            }
            Status.SUCCESS -> {
                val currentGameInfoDTO = result.data as? CurrentGameInfo

                currentGameInfoDTO?.let {
                    listener?.onCurrentGameStatus(it, true)
                } ?: run { listener?.onDefaultError(ERROR_DEFAULT) }
            }
        }
    }

    suspend fun getHistory(
        accountId: String,
        startIndex: Int,
        endIndex: Int,
        onResponse: (MatchList) -> Unit
    ) {
        this.accountId = accountId
        val result = repository.getMatchListById(accountId, startIndex, endIndex)
        if (champions == null) champions = getChampions()

        when (result.status) {
            Status.ERROR -> listener?.onDefaultError(error = result.message ?: ERROR_DEFAULT)
            Status.SUCCESS -> {
                val list = result.data as? MatchListDTO

                list?.let { matchListDTO ->
                    val matchList = matchListDTO.toMatchList(champions)
                    onResponse(matchList)
                } ?: run { listener?.onDefaultError(ERROR_DEFAULT) }
            }
        }
    }

    fun setHomeLoading(isLoading: Boolean) {
        listener?.setHistoryLoading(isLoading)
    }

    suspend fun getDetailMatch(gameId: Long): Match? {
        val result = repository.getDetailMatchById(gameId)
        if (champions == null) champions = getChampions()
        if (items == null) items = getItems()

        return when (result.status) {
            Status.ERROR -> {
                listener?.onDefaultError(result.message ?: ERROR_DEFAULT)
                null
            }
            Status.SUCCESS -> {
                val matchDTO = result.data as? MatchDTO

                return matchDTO?.let {
                    return it.toMatch(
                        champions = champions,
                        items = items,
                        id = accountId
                    )
                } ?: run {
                    listener?.onDefaultError(ERROR_DEFAULT)
                    return null
                }
            }
        }
    }

    private suspend fun getItems(): List<Item>? {
        val result = repository.getItems()

        return when (result.status) {
            Status.ERROR -> {
                listener?.onDefaultError(error = result.message ?: ERROR_DEFAULT)
                null
            }
            Status.SUCCESS -> {
                val itemsMap = (result.data as? Items)?.data.serializeToMap()
                return itemsMap.toItemsDTO().items.map { it.toItemGenericObject() }
            }
        }
    }

    private suspend fun getChampions(): List<Champion>? {
        val result = repository.getChampions()

        return when (result.status) {
            Status.ERROR -> {
                listener?.onDefaultError(error = result.message ?: ERROR_DEFAULT)
                null
            }
            Status.SUCCESS -> {
                val championsMap = (result.data as? Champions)?.data.serializeToMap()
                return championsMap.toChampionsDTO().champions.map { it.toChampionGenericObject() }
            }
        }
    }
}