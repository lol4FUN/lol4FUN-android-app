package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SummonerInfo(
    val accountId: String,
    val id: String,
    val name: String,
    val profileIconId: Int,
    val puuid: String,
    val revisionDate: Long,
    val summonerLevel: Int
): Parcelable