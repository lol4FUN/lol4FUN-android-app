package com.github.lol4fun.core.model.dto

import com.github.lol4fun.core.model.ParticipantIdentity

data class ParticipantIdentityDTO(
    val player: PlayerDTO,
    val participantId: Int
)

fun ParticipantIdentityDTO.toParticipantIdentity(): ParticipantIdentity {
    return ParticipantIdentity(
        player = player.toPlayer(),
        id = participantId
    )
}