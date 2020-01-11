package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamBans(
    val pickTurn: Int,
    val champion: Champion?
) : Parcelable