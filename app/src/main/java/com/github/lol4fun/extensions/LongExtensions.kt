package com.github.lol4fun.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateString(): String {
    return try {
        val date = Date(this)
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US)
        sdf.format(date)
    } catch (e: Exception) {
        ""
    }
}

fun Long.secondsToFormattedTime(): String {
    return "${(this / 3600) % 24}:${(this / 60) % 60}:${this % 60}"
}

fun Long.millisecondsToFormattedTime(): String {
    return "${(this / (3600 * 1000)) % 24}:${(this / (60 * 1000)) % 60}:${(this / 1000) % 60}"
}