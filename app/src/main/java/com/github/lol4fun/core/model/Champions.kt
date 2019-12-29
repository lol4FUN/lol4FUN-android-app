package com.github.lol4fun.core.model

import com.github.champions.model.Data

data class Champions(
    val `data`: Data,
    val format: String,
    val type: String,
    val version: String
)