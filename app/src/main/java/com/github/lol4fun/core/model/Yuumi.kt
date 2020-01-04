package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Yuumi(
    val blurb: String,
    val id: String,
    val key: String,
    val name: String,
    val partype: String,
    val image: Image,
    val tags: List<String>,
    val title: String,
    val version: String
): Parcelable