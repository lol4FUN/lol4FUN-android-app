package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchReference(
    val lane: String,
    val gameId: Long,
    val champion: Champion?,
    val date: String
): Parcelable