package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchList(
    val matches: List<MatchReference>,
    val totalGames: Int,
    val startIndex: Int,
    val endedIndex: Int
): Parcelable