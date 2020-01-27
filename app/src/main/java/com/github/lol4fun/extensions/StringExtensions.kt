package com.github.lol4fun.extensions

import android.text.Editable

fun String.getTextByEditable(): Editable {
    return Editable.Factory.getInstance().newEditable(this)
}