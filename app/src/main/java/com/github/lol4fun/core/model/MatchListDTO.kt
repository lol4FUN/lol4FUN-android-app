package com.github.lol4fun.core.model


data class MatchListDTO(
    val matches: List<MatchReferenceDTO>,
    val totalGames: Int,
    val startIndex: Int,
    val endedIndex: Int
)

fun MatchListDTO.toMatchList(champions: List<Champion>): MatchList {
    return MatchList(
        matches = matches.map { it.toMatchReference(champions) },
        totalGames = totalGames,
        startIndex = startIndex,
        endedIndex = endedIndex
    )
}