package com.github.lol4fun.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.lang.NumberFormatException

@Parcelize
data class Item(
    val description: String,
    val gold: Gold,
    val image: Image,
    val name: String,
    val plaintext: String,
    val tags: List<String>
): Parcelable

fun Item.getId(): Int {
    return try {
        image.full.removeRange(4..7).toInt()
    } catch (e: NumberFormatException) { 0 }
}