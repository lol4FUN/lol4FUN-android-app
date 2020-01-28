package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Perks(
    val perkStyle: Long,        //Primary runes path
    val perkIds: List<Long>,    //IDs of the perks/runes assigned.
    val perkSubStyle: Long      //Secondary runes path
): Parcelable