package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Server(
    val riotCode: String,
    val code: Int,
    val name: Int
): Parcelable