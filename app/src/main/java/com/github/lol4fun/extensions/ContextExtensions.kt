package com.github.lol4fun.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.showSnackBar(view: View, resourceId: Int) {
    Snackbar.make(view, getString(resourceId), Snackbar.LENGTH_SHORT).show()
}

fun Context.showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(resourceId: Int) {
    Toast.makeText(this, getString(resourceId), Toast.LENGTH_SHORT).show()
}

fun Context.hideKeyboard(view: View?) {
    view?.let {
        it.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}