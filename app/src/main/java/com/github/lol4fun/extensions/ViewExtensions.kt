package com.github.lol4fun.extensions

import android.content.Context
import android.widget.ImageView
import com.github.lol4fun.util.GlideApp

fun ImageView.setImageDownload(context: Context?, path: String) {
    context?.let {
        GlideApp.with(it).load(path).into(this)
    }
}