package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Customer (
    var name: String? = null,
    var email: String? = null,
    var summonerName: String? = null,
    var accountId: String? = null,
    var id: String? = null,
    var premiumUser: Boolean? = null,
    var profilePicture: String? = null,
    var colorPreference: Long? = null,
    var profileIconId: Long? = null
): Parcelable