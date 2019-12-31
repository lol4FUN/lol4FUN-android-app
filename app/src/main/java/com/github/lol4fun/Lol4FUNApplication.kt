package com.github.lol4fun

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.github.lol4fun.di.businessModule
import com.github.lol4fun.di.repositoryModule
import com.github.lol4fun.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Lol4FUNApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Lol4FUNApplication)
            modules(listOf(businessModule, repositoryModule, viewModelModule))
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}