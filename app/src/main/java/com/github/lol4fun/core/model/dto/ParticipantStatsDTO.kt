package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.ParticipantStats

data class ParticipantStatsDTO(
    val id: Long
)

fun ParticipantStatsDTO.toParticipantStats(): ParticipantStats {
    return ParticipantStats(id)
}