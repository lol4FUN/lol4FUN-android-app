package com.github.lol4fun.core.model.items

import android.os.Parcelable
import com.github.lol4fun.core.model.Gold
import com.github.lol4fun.core.model.Image
import kotlinx.android.parcel.Parcelize

@Parcelize
data class X3742(
    val id: Int = 3742,
    val description: String,
    val gold: Gold,
    val image: Image,
    val name: String,
    val plaintext: String,
    val tags: List<String>
): Parcelable