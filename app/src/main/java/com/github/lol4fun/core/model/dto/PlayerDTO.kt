package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Player

data class PlayerDTO(
    val summonerName: String,
    val matchHistoryUri: String,
    val platformId: String,
    val profileIcon: Int,
    val summonerId: String,
    val accountId: String
)

fun PlayerDTO.toPlayer(id: String): Player {
    return Player(
        summonerName = summonerName,
        matchHistoryUri = matchHistoryUri,
        platformId = platformId,
        profileIcon = profileIcon,
        summonerId = summonerId,
        accountId = accountId,
        user = id == accountId
    )
}