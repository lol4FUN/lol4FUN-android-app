package com.github.lol4fun.core.model

import com.github.lol4fun.extensions.toDateString


data class MatchReferenceDTO(
    val lane: String,
    val gameId: Long,
    val champion: Int,
    val timestamp: Long
)

fun MatchReferenceDTO.toMatchReference(champions: List<Champion>): MatchReference {
    return MatchReference(
        lane = setLaneByName(lane),
        gameId = gameId,
        champion = setChampionByKey(champion, champions),
        date = timestamp.toDateString()
    )
}

private fun setLaneByName(name: String): Int {
    TODO()
}

private fun setChampionByKey(key: Int, champions: List<Champion>): Champion {
    TODO()
}

