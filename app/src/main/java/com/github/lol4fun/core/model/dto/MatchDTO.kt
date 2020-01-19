package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.extensions.secondsToFormattedTime
import com.github.lol4fun.extensions.setServerByCode

data class MatchDTO(
    val gameId: Long,
    val participantIdentities: List<ParticipantIdentityDTO>,
    val platformId: String,
    val gameMode: String,
    val gameType: String,
    val teams: List<TeamStatsDTO>,
    val participants: List<ParticipantDTO>,
    val gameDuration: Long  //Seconds
)

fun MatchDTO.toMatch(
    champions: List<Champion>?,
    lane: Int?,
    id: String
): Match {
    return Match(
        gameId = gameId,
        participant = participantIdentities.map { it.toParticipantIdentity(id) },
        server = platformId.setServerByCode(),
        gameMode = gameMode,
        gameType = gameType,
        teams = teams.map { it.toTeamStats(champions) },
        participants = participants.map { it.toParticipant(champions) },
        gameDuration = gameDuration.secondsToFormattedTime(),
        lane = lane
    )
}
