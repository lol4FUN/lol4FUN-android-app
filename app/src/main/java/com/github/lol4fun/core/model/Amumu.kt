package com.github.lol4fun.core.model

data class Amumu(
    val blurb: String,
    val id: String,
    val key: String,
    val name: String,
    val partype: String,
    val image: Image,
    val tags: List<String>,
    val title: String,
    val version: String
)