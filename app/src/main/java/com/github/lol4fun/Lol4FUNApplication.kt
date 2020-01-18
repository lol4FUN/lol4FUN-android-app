package com.github.lol4fun

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.github.lol4fun.core.di.businessModule
import com.github.lol4fun.core.di.repositoryModule
import com.github.lol4fun.core.di.viewModelModule
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.util.ConstantsUtil
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_DARK
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Lol4FUNApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Lol4FUNApplication)
            modules(listOf(businessModule, repositoryModule, viewModelModule))
        }

        setDarkModeFromUserPreference()
    }

    private fun setDarkModeFromUserPreference() {
        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        val docRef = db
            .collection(ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS)
            .document(auth.currentUser?.uid ?: "")

        // Source can be CACHE, SERVER, or DEFAULT.
        val source = Source.CACHE

        // Get the document, forcing the SDK to use the offline cache
        docRef.get(source).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Document found in the offline cache
                val customer = task.result?.toObject(Customer::class.java)
                if (customer?.colorPreference == FIELD_USER_COLOR_PREFERENCE_DARK) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}