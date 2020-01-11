package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.TeamBans

data class TeamBansDTO(
    val pickTurn: Int,
    val championId: Int
)

fun TeamBansDTO.toTeamBans(champions: List<Champion>): TeamBans {
    return TeamBans(
        pickTurn = pickTurn,
        champion = champions.find { it.key == championId.toString() }
    )
}