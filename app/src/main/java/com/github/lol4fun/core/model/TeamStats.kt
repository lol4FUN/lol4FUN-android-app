package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamStats(
    val firstDragon: Boolean,
    val firstInhibitor: Boolean,
    val bans: List<TeamBans>,
    val baronKills: Int,
    val firstRiftHerald: Boolean,
    val firstBaron: Boolean,
    val riftHeraldKills: Int,
    val firstBlood: Boolean,
    val teamSide: String,
    val firstTower: Boolean,
    val vilemawKills: Int,
    val inhibitorKills: Int,
    val towerKills: Int,
    val dominionVictoryScore: Int,
    val win: Boolean,
    val lose: Boolean,
    val dragonKills: String
): Parcelable