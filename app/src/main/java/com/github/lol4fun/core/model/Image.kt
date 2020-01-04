package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val full: String,
    val group: String,
    val h: Int,
    val sprite: String,
    val w: Int,
    val x: Int,
    val y: Int
): Parcelable