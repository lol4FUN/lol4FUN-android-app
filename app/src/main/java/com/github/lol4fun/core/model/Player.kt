package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player (
    val summonerName: String,
    val matchHistoryUri: String,
    val platformId: String,
    val profileIcon: Int,
    val summonerId: String,
    val accountId: String
): Parcelable