package com.github.lol4fun

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class Lol4FUNApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}