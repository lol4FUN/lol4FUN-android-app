package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BannedChampion(
    val pickTurn: Int,
    val champion: Champion?,
    val teamSide: String
): Parcelable