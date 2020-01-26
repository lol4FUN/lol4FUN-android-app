package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val id: Int,
    val description: String,
    val gold: Gold,
    val image: Image,
    val name: String,
    val plaintext: String,
    val tags: List<String>
): Parcelable