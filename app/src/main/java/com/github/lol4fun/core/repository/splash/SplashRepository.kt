package com.github.lol4fun.core.repository.splash

import com.github.lol4fun.core.base.BaseRepository
import com.github.lol4fun.util.ConstantsUtil
import java.util.*

class SplashRepository : BaseRepository() {

    fun userIsLogged(): Boolean {
        return auth.currentUser != null
    }
}