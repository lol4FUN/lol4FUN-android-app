package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.CurrentGameInfo
import com.github.lol4fun.extensions.millisecondsToFormattedTime
import com.github.lol4fun.extensions.secondsToFormattedTime
import com.github.lol4fun.extensions.setServerByCode

data class CurrentGameInfoDTO(
    val gameId: Long,
    val gameStartTime: Long,        //The game start time represented in epoch milliseconds
    val platformId: String,
    val gameMode: String,
    val mapId: Long,
    val gameType: String,
    val bannedChampions: List<BannedChampionDTO>,
    val participants: List<CurrentGameParticipantDTO>,
    val gameLength: Long,           //The amount of time in seconds that has passed since the game started
    val gameQueueConfigId: Long     //The queue type (queue types are documented on the Game Constants page)
)

fun CurrentGameInfoDTO.toCurrentGameInfo(champions: List<Champion>): CurrentGameInfo {
    return CurrentGameInfo(
        gameId = gameId,
        gameStartTime = gameStartTime.millisecondsToFormattedTime(),
        server = platformId.setServerByCode(),
        gameType = gameType,
        gameMode = gameMode,
        mapId = mapId,
        bannedChampions = bannedChampions.map { it.toBannedChampion(champions) },
        participants = participants.map { it.toCurrentGameParticipant(champions) },
        gameLength = gameLength.secondsToFormattedTime(),
        gameQueueConfigId = gameQueueConfigId
    )
}

