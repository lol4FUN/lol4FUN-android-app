package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentGameParticipant(
    val profileIconId: Long,
    val champion: Champion?,
    val summonerName: String,
    val bot: Boolean,
    val perks: Perks,
    val spell1Id: Long,
    val spell2Id: Long,
    val teamSide: String,
    val summonerId: String
): Parcelable