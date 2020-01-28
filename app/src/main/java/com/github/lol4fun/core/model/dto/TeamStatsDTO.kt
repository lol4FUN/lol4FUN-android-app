package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.TeamStats
import com.github.lol4fun.util.ConstantsUtil.Match.BLUE_SIDE
import com.github.lol4fun.util.ConstantsUtil.Match.FLAG_LOSE
import com.github.lol4fun.util.ConstantsUtil.Match.RED_SIDE
import com.github.lol4fun.util.ConstantsUtil.Match.FLAG_WIN

data class TeamStatsDTO(
    val firstDragon: Boolean,
    val firstInhibitor: Boolean,
    val bans: List<TeamBansDTO>,
    val baronKills: Int,
    val firstRiftHerald: Boolean,
    val firstBaron: Boolean,
    val riftHeraldKills: Int,
    val firstBlood: Boolean,
    val teamId: Int,    //100 for blue side; 200 for red side
    val firstTower: Boolean,
    val vilemawKills: Int,
    val inhibitorKills: Int,
    val towerKills: Int,
    val dominionVictoryScore: Int,
    val win: String,    //Fail or Win
    val dragonKills: String
)

fun TeamStatsDTO.toTeamStats(champions: List<Champion>?): TeamStats {
    return TeamStats(
        firstDragon = firstDragon,
        firstInhibitor = firstInhibitor,
        firstRiftHerald = firstRiftHerald,
        firstBaron = firstBaron,
        firstBlood = firstBlood,
        firstTower = firstTower,
        bans = bans.map { it.toTeamBans(champions) },
        baronKills = baronKills,
        vilemawKills = vilemawKills,
        inhibitorKills = inhibitorKills,
        towerKills = towerKills,
        dragonKills = dragonKills,
        riftHeraldKills = riftHeraldKills,
        teamSide = if (teamId == 100) BLUE_SIDE else RED_SIDE,
        dominionVictoryScore = dominionVictoryScore,
        win = win == FLAG_WIN,
        lose = win == FLAG_LOSE
    )
}