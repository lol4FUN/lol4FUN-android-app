package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParticipantIdentity (
    val player: Player,
    val id: Int
): Parcelable