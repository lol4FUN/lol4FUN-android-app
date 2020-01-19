package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.MatchReference
import com.github.lol4fun.extensions.toDateString

data class MatchReferenceDTO(
    val lane: String,
    val gameId: Long,
    val champion: Int,
    val timestamp: Long
)

fun MatchReferenceDTO.toMatchReference(champions: List<Champion>?): MatchReference {
    return MatchReference(
        lane = setLaneByName(lane),
        gameId = gameId,
        champion = champions?.find { it.key == champion.toString() },
        date = timestamp.toDateString()
    )
}

private fun setLaneByName(name: String): Int {
    return 1
}

