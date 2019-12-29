package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Customer (
    val name: String? = null,
    val email: String? = null,
    val summonerName: String? = null,
    val accountId: String? = null,
    val id: String? = null,
    val premiumUser: Boolean? = null,
    val profilePicture: String? = null,
    val colorPreference: Long? = null,
    val profileIconId: Long? = null
): Parcelable