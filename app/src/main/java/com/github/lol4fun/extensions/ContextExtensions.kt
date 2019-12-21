package com.github.lol4fun.extensions

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Context.showSnackBar(view: View, resourceId: Int) {
    Snackbar.make(view, getString(resourceId), Snackbar.LENGTH_SHORT).show()
}