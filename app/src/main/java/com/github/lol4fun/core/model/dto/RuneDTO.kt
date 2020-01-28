package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Rune

data class RuneDTO(
    val runeId: Int,
    val rank: Int
)

fun RuneDTO.toRune(): Rune {
    return Rune(runeId, rank)
}