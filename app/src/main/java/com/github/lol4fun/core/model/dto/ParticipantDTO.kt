package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.Item
import com.github.lol4fun.core.model.Participant
import com.github.lol4fun.util.ConstantsUtil.Match.BLUE_SIDE
import com.github.lol4fun.util.ConstantsUtil.Match.RED_SIDE

data class ParticipantDTO(
    val stats: ParticipantStatsDTO,
    val participantId: Int,
    val runes: List<RuneDTO>?,
    val teamId: Int,
    val spell1Id: Int,
    val spell2Id: Int,
    val highestAchievedSeasonTier: String?,
    val championId: Int
)

fun ParticipantDTO.toParticipant(champions: List<Champion>?, items: List<Item>?): Participant {
    return Participant(
        stats = stats.toParticipantStats(items),
        participantId = participantId,
        runes = runes?.map { it.toRune() },
        teamSize = if (teamId == 100) BLUE_SIDE else RED_SIDE,
        spell1Id = spell1Id,
        spell2Id = spell2Id,
        highestAchievedSeasonTier = highestAchievedSeasonTier,
        champion = champions?.find { it.key == championId.toString() }
    )
}