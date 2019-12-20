package com.github.lol4fun.util

class ConstantsUtil {

    object Main {
        const val RC_SIGN_IN = 123
    }

    object FirestoreDataBaseNames{
        const val DATABASE_CUSTOMERS = "customers"
    }

    object FirestoreDataBaseFields{
        const val FIELD_USER_NAME = "name"
        const val FIELD_USER_EMAIL = "email"
        const val FIELD_USER_PREMIUM = "premiumUser"
        const val FIELD_USER_COLOR_PREFERENCE = "colorPreference"
        const val FIELD_USER_COLOR_PREFERENCE_DARK = 1
        const val FIELD_USER_COLOR_PREFERENCE_LIGHT = 2
    }
}