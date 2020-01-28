package com.github.lol4fun.core.model.champions

import android.os.Parcelable
import com.github.lol4fun.core.model.Image
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Azir(
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