package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentGameInfo(
    val gameId: Long,
    val gameStartTime: String,
    val server: Server?,
    val gameMode: String,
    val mapId: Long,
    val gameType: String,
    val bannedChampions: List<BannedChampion>,
    val participants: List<CurrentGameParticipant>,
    val gameLength: String,
    val gameQueueConfigId: Long     //The queue type (queue types are documented on the Game Constants page)
): Parcelable