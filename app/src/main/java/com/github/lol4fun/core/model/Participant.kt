package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Participant(
    val stats: ParticipantStats,
    val participantId: Int,
    val runes: List<Rune>?,
    val teamSize: String,
    val spell1Id: Int,
    val spell2Id: Int,
    val highestAchievedSeasonTier: String?,
    val champion: Champion?
): Parcelable