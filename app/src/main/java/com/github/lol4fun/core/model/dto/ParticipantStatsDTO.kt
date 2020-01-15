package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.ParticipantStats

data class ParticipantStatsDTO(
    val participantId: Int,
    val win: Boolean,
    val champLevel: Int,
    val firstTowerKill: Boolean,
    val firstTowerAssist: Boolean,
    val firstBloodKill: Boolean,
    val firstBloodAssist: Boolean,
    val firstInhibitorKill: Boolean,
    val firstInhibitorAssist: Boolean,
    val visionScore: Long,
    val magicDamageDealtToChampions: Long,
    val physicalDamageDealtToChampions: Long,
    val damageDealtToObjectives: Long,
    val damageDealtToTurrets: Long,
    val magicDamageDealt: Long,
    val physicalDamageDealt: Long,
    val damageSelfMitigated: Long,
    val magicalDamageTaken: Long,
    val trueDamageTaken: Long,
    val trueDamageDealtToChampions: Long,
    val physicalDamageTaken: Long,
    val trueDamageDealt: Long,
    val totalDamageTaken: Long,
    val totalDamageDealt: Long,
    val totalDamageDealtToChampions: Long,
    val timeCCingOthers: Long,
    val totalTimeCrowdControlDealt: Int,
    val longestTimeSpentLiving: Int,
    val nodeNeutralizeAssist: Int,
    val nodeCapture: Int,
    val nodeCaptureAssist: Int,
    val nodeNeutralize: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val totalMinionsKilled: Int,
    val totalUnitsHealed: Int,
    val totalHeal: Long,
    val wardsKilled: Int,
    val wardsPlaced: Int,
    val turretKills: Int,
    val totalScoreRank: Int,
    val neutralMinionsKilled: Int,
    val killingSprees: Int,
    val unrealKills: Int,
    val doubleKills: Int,
    val tripleKills: Int,
    val quadraKills: Int,
    val pentaKills: Int,
    val largestMultiKill: Int,
    val largestCriticalStrike: Int,
    val largestKillingSpree: Int,
    val inhibitorKills: Int,
    val teamObjective: Int,
    val combatPlayerScore: Int,
    val goldSpent: Int,
    val goldEarned: Int,
    val objectivePlayerScore: Int,
    val totalPlayerScore: Int,
    val neutralMinionsKilledTeamJungle: Int,
    val neutralMinionsKilledEnemyJungle: Int,
    val sightWardsBoughtInGame: Int,
    val altarsCaptured: Int,
    val altarsNeutralized: Int,
    val visionWardsBoughtInGame: Int,
    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,
    val playerScore0: Int,
    val playerScore1: Int,
    val playerScore2: Int,
    val playerScore3: Int,
    val playerScore4: Int,
    val playerScore5: Int,
    val playerScore6: Int,
    val playerScore7: Int,
    val playerScore8: Int,
    val playerScore9: Int,
    val perkPrimaryStyle: Int,  //Primary rune path
    val perkSubStyle: Int,      //Secondary rune path
    val perk0: Int,             //Primary path keystone rune.
    val perk1: Int,             //Primary path rune.
    val perk2: Int,
    val perk3: Int,
    val perk4: Int,             //Secondary path rune.
    val perk5: Int,
    val perk0Var1: Int,         //Post game rune stats.
    val perk0Var2: Int,
    val perk0Var3: Int,
    val perk1Var1: Int,
    val perk1Var2: Int,
    val perk1Var3: Int,
    val perk2Var1: Int,
    val perk2Var2: Int,
    val perk2Var3: Int,
    val perk3Var1: Int,
    val perk3Var2: Int,
    val perk3Var3: Int,
    val perk4Var1: Int,
    val perk4Var2: Int,
    val perk4Var3: Int,
    val perk5Var1: Int,
    val perk5Var2: Int,
    val perk5Var3: Int
)

fun ParticipantStatsDTO.toParticipantStats(): ParticipantStats {
    return ParticipantStats(
        id = participantId,
        win = win,
        champLevel = champLevel,
        firstTowerKill = firstTowerKill,
        firstTowerAssist = firstTowerAssist,
        firstBloodKill = firstBloodKill,
        firstBloodAssist = firstBloodAssist,
        firstInhibitorKill = firstInhibitorKill,
        firstInhibitorAssist = firstInhibitorAssist,
        visionScore = visionScore,
        magicDamageDealtToChampions = magicDamageDealtToChampions,
        physicalDamageDealtToChampions = physicalDamageDealtToChampions,
        damageDealtToObjectives = damageDealtToObjectives,
        damageDealtToTurrets = damageDealtToTurrets,
        magicDamageDealt = magicDamageDealt,
        physicalDamageDealt = physicalDamageDealt,
        damageSelfMitigated = damageSelfMitigated,
        magicalDamageTaken = magicalDamageTaken,
        trueDamageTaken = trueDamageTaken,
        trueDamageDealtToChampions = trueDamageDealtToChampions,
        physicalDamageTaken = physicalDamageTaken,
        trueDamageDealt = trueDamageDealt,
        totalDamageTaken = totalDamageTaken,
        totalDamageDealt = totalDamageDealt,
        totalDamageDealtToChampions = totalDamageDealtToChampions,
        timeCCingOthers = timeCCingOthers,
        totalTimeCrowdControlDealt = totalTimeCrowdControlDealt,
        longestTimeSpentLiving = longestTimeSpentLiving,
        nodeNeutralizeAssist = nodeNeutralizeAssist,
        nodeCapture = nodeCapture,
        nodeCaptureAssist = nodeCaptureAssist,
        nodeNeutralize = nodeNeutralize,
        kills = kills,
        deaths = deaths,
        assists = assists,
        totalMinionsKilled = totalMinionsKilled,
        totalUnitsHealed = totalUnitsHealed,
        totalHeal = totalHeal,
        wardsKilled = wardsKilled,
        wardsPlaced = wardsPlaced,
        turretKills = turretKills,
        totalScoreRank = totalScoreRank,
        neutralMinionsKilled = neutralMinionsKilled,
        killingSprees = killingSprees,
        unrealKills = unrealKills,
        doubleKills = doubleKills,
        tripleKills = tripleKills,
        quadraKills = quadraKills,
        pentaKills = pentaKills,
        largestMultiKill = largestMultiKill,
        largestCriticalStrike = largestCriticalStrike,
        largestKillingSpree = largestKillingSpree,
        inhibitorKills = inhibitorKills,
        teamObjective = teamObjective,
        combatPlayerScore = combatPlayerScore,
        goldSpent = goldSpent,
        goldEarned = goldEarned,
        objectivePlayerScore = objectivePlayerScore,
        totalPlayerScore = totalPlayerScore,
        neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle,
        neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle,
        sightWardsBoughtInGame = sightWardsBoughtInGame,
        altarsCaptured = altarsCaptured,
        altarsNeutralized = altarsNeutralized,
        visionWardsBoughtInGame = visionWardsBoughtInGame,
        item0 = item0,
        item1 = item1,
        item2 = item2,
        item3 = item3,
        item4 = item4,
        item5 = item5,
        item6 = item6,
        playerScore0 = playerScore0,
        playerScore1 = playerScore1,
        playerScore2 = playerScore2,
        playerScore3 = playerScore3,
        playerScore4 = playerScore4,
        playerScore5 = playerScore5,
        playerScore6 = playerScore6,
        playerScore7 = playerScore7,
        playerScore8 = playerScore8,
        playerScore9 = playerScore9,
        perkPrimaryStyle = perkPrimaryStyle,
        perkSubStyle = perkSubStyle,
        perk0 = perk0,
        perk1 = perk1,
        perk2 = perk2,
        perk3 = perk3,
        perk4 = perk4,
        perk5 = perk5,
        perk0Var1 = perk0Var1,
        perk0Var2 = perk0Var2,
        perk0Var3 = perk0Var3,
        perk1Var1 = perk1Var1,
        perk1Var2 = perk1Var2,
        perk1Var3 = perk1Var3,
        perk2Var1 = perk2Var1,
        perk2Var2 = perk2Var2,
        perk2Var3 = perk2Var3,
        perk3Var1 = perk3Var1,
        perk3Var2 = perk3Var2,
        perk3Var3 = perk3Var3,
        perk4Var1 = perk4Var1,
        perk4Var2 = perk4Var2,
        perk4Var3 = perk4Var3,
        perk5Var1 = perk5Var1,
        perk5Var2 = perk5Var2,
        perk5Var3 = perk5Var3
    )
}