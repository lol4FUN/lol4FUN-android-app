package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.Perks

data class PerksDTO(
    val perkStyle: Long,        //Primary runes path
    val perkIds: List<Long>,    //IDs of the perks/runes assigned.
    val perkSubStyle: Long      //Secondary runes path
)

fun PerksDTO.toPerks(): Perks {
    return Perks(
        perkStyle,
        perkIds,
        perkSubStyle
    )
}