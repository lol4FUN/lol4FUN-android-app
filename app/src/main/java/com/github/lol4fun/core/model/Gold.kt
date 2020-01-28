package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gold(
    val base: Int,
    val purchasable: Boolean,
    val sell: Int,
    val total: Int
): Parcelable