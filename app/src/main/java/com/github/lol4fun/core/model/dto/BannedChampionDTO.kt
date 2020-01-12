package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.BannedChampion
import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.util.ConstantsUtil.Match.BLUE_SIDE
import com.github.lol4fun.util.ConstantsUtil.Match.RED_SIDE

data class BannedChampionDTO(
    val pickTurn: Int,
    val championId: Long,
    val teamId: Long
)

fun BannedChampionDTO.toBannedChampion(champions: List<Champion>): BannedChampion {
    return BannedChampion(
        pickTurn = pickTurn,
        champion = champions.find { it.key == championId.toString() },
        teamSide = if (teamId == 100L) BLUE_SIDE else RED_SIDE
    )
}