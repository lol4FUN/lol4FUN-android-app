package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Champions(
    val `data`: ChampionsData,
    val format: String,
    val type: String,
    val version: String
): Parcelable