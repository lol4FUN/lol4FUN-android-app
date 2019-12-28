package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiError(
    var statusCode: Int? = null,
    var message: String? = null
): Parcelable