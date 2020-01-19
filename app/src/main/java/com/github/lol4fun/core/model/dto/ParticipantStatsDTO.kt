package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.ParticipantStats

data class ParticipantStatsDTO(
    val assists: Int,
    val champLevel: Int,
    val combatPlayerScore: Int,
    val damageDealtToObjectives: Int,
    val damageDealtToTurrets: Int,
    val damageSelfMitigated: Int,
    val deaths: Int,
    val doubleKills: Int,
    val firstBloodAssist: Boolean,
    val firstBloodKill: Boolean,
    val firstInhibitorAssist: Boolean,
    val firstInhibitorKill: Boolean,
    val firstTowerAssist: Boolean,
    val firstTowerKill: Boolean,
    val goldEarned: Int,
    val goldSpent: Int,
    val inhibitorKills: Int,
    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,
    val killingSprees: Int,
    val kills: Int,
    val largestCriticalStrike: Int,
    val largestKillingSpree: Int,
    val largestMultiKill: Int,
    val longestTimeSpentLiving: Int,
    val magicDamageDealt: Int,
    val magicDamageDealtToChampions: Int,
    val magicalDamageTaken: Int,
    val neutralMinionsKilled: Int,
    val neutralMinionsKilledEnemyJungle: Int,
    val neutralMinionsKilledTeamJungle: Int,
    val objectivePlayerScore: Int,
    val participantId: Int,
    val pentaKills: Int,
    val perk0: Int,
    val perk0Var1: Int,
    val perk0Var2: Int,
    val perk0Var3: Int,
    val perk1: Int,
    val perk1Var1: Int,
    val perk1Var2: Int,
    val perk1Var3: Int,
    val perk2: Int,
    val perk2Var1: Int,
    val perk2Var2: Int,
    val perk2Var3: Int,
    val perk3: Int,
    val perk3Var1: Int,
    val perk3Var2: Int,
    val perk3Var3: Int,
    val perk4: Int,
    val perk4Var1: Int,
    val perk4Var2: Int,
    val perk4Var3: Int,
    val perk5: Int,
    val perk5Var1: Int,
    val perk5Var2: Int,
    val perk5Var3: Int,
    val perkPrimaryStyle: Int,
    val perkSubStyle: Int,
    val physicalDamageDealt: Int,
    val physicalDamageDealtToChampions: Int,
    val physicalDamageTaken: Int,
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
    val quadraKills: Int,
    val sightWardsBoughtInGame: Int,
    val statPerk0: Int,
    val statPerk1: Int,
    val statPerk2: Int,
    val timeCCingOthers: Int,
    val totalDamageDealt: Int,
    val totalDamageDealtToChampions: Int,
    val totalDamageTaken: Int,
    val totalHeal: Int,
    val totalMinionsKilled: Int,
    val totalPlayerScore: Int,
    val totalScoreRank: Int,
    val totalTimeCrowdControlDealt: Int,
    val totalUnitsHealed: Int,
    val tripleKills: Int,
    val trueDamageDealt: Int,
    val trueDamageDealtToChampions: Int,
    val trueDamageTaken: Int,
    val turretKills: Int,
    val unrealKills: Int,
    val visionScore: Int,
    val visionWardsBoughtInGame: Int,
    val wardsKilled: Int,
    val wardsPlaced: Int,
    val win: Boolean
)

fun ParticipantStatsDTO.toParticipantStats(): ParticipantStats {
    return ParticipantStats(
        assists,
        champLevel,
        combatPlayerScore,
        damageDealtToObjectives,
        damageDealtToTurrets,
        damageSelfMitigated,
        deaths,
        doubleKills,
        firstBloodAssist,
        firstBloodKill,
        firstInhibitorAssist,
        firstInhibitorKill,
        firstTowerAssist,
        firstTowerKill,
        goldEarned,
        goldSpent,
        inhibitorKills,
        item0,
        item1,
        item2,
        item3,
        item4,
        item5,
        item6,
        killingSprees,
        kills,
        largestCriticalStrike,
        largestKillingSpree,
        largestMultiKill,
        longestTimeSpentLiving,
        magicDamageDealt,
        magicDamageDealtToChampions,
        magicalDamageTaken,
        neutralMinionsKilled,
        neutralMinionsKilledEnemyJungle,
        neutralMinionsKilledTeamJungle,
        objectivePlayerScore,
        participantId,
        pentaKills,
        perk0,
        perk0Var1,
        perk0Var2,
        perk0Var3,
        perk1,
        perk1Var1,
        perk1Var2,
        perk1Var3,
        perk2,
        perk2Var1,
        perk2Var2,
        perk2Var3,
        perk3,
        perk3Var1,
        perk3Var2,
        perk3Var3,
        perk4,
        perk4Var1,
        perk4Var2,
        perk4Var3,
        perk5,
        perk5Var1,
        perk5Var2,
        perk5Var3,
        perkPrimaryStyle,
        perkSubStyle,
        physicalDamageDealt,
        physicalDamageDealtToChampions,
        physicalDamageTaken,
        playerScore0,
        playerScore1,
        playerScore2,
        playerScore3,
        playerScore4,
        playerScore5,
        playerScore6,
        playerScore7,
        playerScore8,
        playerScore9,
        quadraKills,
        sightWardsBoughtInGame,
        statPerk0,
        statPerk1,
        statPerk2,
        timeCCingOthers,
        totalDamageDealt,
        totalDamageDealtToChampions,
        totalDamageTaken,
        totalHeal,
        totalMinionsKilled,
        totalPlayerScore,
        totalScoreRank,
        totalTimeCrowdControlDealt,
        totalUnitsHealed,
        tripleKills,
        trueDamageDealt,
        trueDamageDealtToChampions,
        trueDamageTaken,
        turretKills,
        unrealKills,
        visionScore,
        visionWardsBoughtInGame,
        wardsKilled,
        wardsPlaced,
        win
    )
}