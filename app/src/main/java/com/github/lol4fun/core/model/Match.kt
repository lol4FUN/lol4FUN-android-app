package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    val gameId: Long,
    val participant: List<ParticipantIdentity>,
    val server: Server?,
    val gameMode: String,
    val gameType: String,
    val teams: List<TeamStats>,
    val participants: List<Participant>,
    val gameDuration: String,
    val lane: Int?
): Parcelable