package com.github.lol4fun.core.model.dto

import com.github.lol4fun.R
import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.core.model.Server
import com.github.lol4fun.util.ConstantsUtil.Match.BR_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.EUN_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.EUW_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.JP_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.KR_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.LAN_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.LAS_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.NA_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.OCE_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.RU_CODE
import com.github.lol4fun.util.ConstantsUtil.Match.TR_CODE

data class MatchDTO(
    val gameId: Long,
    val participantIdentities: List<ParticipantIdentityDTO>,
    val platformId: String,
    val gameMode: String,
    val gameType: String,
    val teams: List<TeamStatsDTO>,
    val participants: List<ParticipantStatsDTO>,
    val gameDuration: Long  //Seconds
)

fun MatchDTO.toMatch(champions: List<Champion>): Match {
    return Match(
        gameId = gameId,
        participant = participantIdentities.map { it.toParticipantIdentity() },
        server = setServer(platformId),
        gameMode = gameMode,
        gameType = gameType,
        teams = teams.map { it.toTeamStats(champions) },
        participantStatsList = participants.map { it.toParticipantStats() },
        gameDuration = convertTimeSeconds(gameDuration)
    )
}

private fun setServer(platformId: String): Server? {
    return when (platformId) {
        BR_CODE -> Server(platformId, R.string.br_code, R.string.br_name)
        RU_CODE -> Server(platformId, R.string.ru_code, R.string.ru_name)
        KR_CODE -> Server(platformId, R.string.kr_code, R.string.kr_name)
        OCE_CODE -> Server(platformId, R.string.oce_code, R.string.oce_name)
        JP_CODE -> Server(platformId, R.string.jp_code, R.string.jp_name)
        NA_CODE -> Server(platformId, R.string.na_code, R.string.na_name)
        EUN_CODE -> Server(platformId, R.string.eun_code, R.string.eun_name)
        EUW_CODE -> Server(platformId, R.string.euw_code, R.string.euw_name)
        TR_CODE -> Server(platformId, R.string.tr_code, R.string.tr_name)
        LAN_CODE -> Server(platformId, R.string.lan_code, R.string.lan_name)
        LAS_CODE -> Server(platformId, R.string.las_code, R.string.las_name)
        else -> null
    }
}

private fun convertTimeSeconds(seconds: Long): String {
    return "${(seconds / 3600) % 24}:${(seconds / 60) % 60}:${seconds % 60}"
}
