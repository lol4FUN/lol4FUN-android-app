package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items(
    val `data`: ItemsData,
    val version: String,
    val type: String
): Parcelable