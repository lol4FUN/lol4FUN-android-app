package com.github.lol4fun.core.model

data class Customer (
    var name: String? = null,
    var email: String? = null,
    var summonerName: String? = null,
    var accountId: String? = null,
    var id: String? = null,
    var premiumUser: Boolean? = null,
    var profilePicture: String? = null,
    var colorPreference: Long? = null
)