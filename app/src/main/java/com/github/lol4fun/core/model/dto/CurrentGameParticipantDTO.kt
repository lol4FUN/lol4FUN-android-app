package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.CurrentGameParticipant
import com.github.lol4fun.util.ConstantsUtil.Match.BLUE_SIDE
import com.github.lol4fun.util.ConstantsUtil.Match.RED_SIDE

data class CurrentGameParticipantDTO(
    val profileIconId: Long,
    val championId: Long,
    val summonerName: String,
    val bot: Boolean,
    val perks: PerksDTO,
    val spell1Id: Long,
    val spell2Id: Long,
    val teamId: Long,
    val summonerId: String
)

fun CurrentGameParticipantDTO.toCurrentGameParticipant(champions: List<Champion>): CurrentGameParticipant {
    return CurrentGameParticipant(
        profileIconId = profileIconId,
        champion = champions.find { it.key == championId.toString() },
        summonerName = summonerName,
        bot = bot,
        perks = perks.toPerks(),
        spell1Id = spell1Id,
        spell2Id = spell2Id,
        teamSide = if (teamId == 100L) BLUE_SIDE else RED_SIDE,
        summonerId = summonerId
    )
}