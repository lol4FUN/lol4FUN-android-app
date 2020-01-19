package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rune(
    val runeId: Int,
    val rank: Int
): Parcelable