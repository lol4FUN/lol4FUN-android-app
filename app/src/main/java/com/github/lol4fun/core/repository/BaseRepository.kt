package com.github.lol4fun.core.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

open class BaseRepository {

    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
}