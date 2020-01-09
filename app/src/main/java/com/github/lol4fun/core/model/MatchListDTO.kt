package com.github.lol4fun.core.model


data class MatchListDTO(
    val matches: List<MatchReferenceDTO>,
    val totalGames: Int,
    val startIndex: Int,
    val endedIndex: Int
)

fun MatchListDTO.toMatchList(): MatchList {
    return MatchList(
        matches = matches.map { it.toMatch() },
        totalGames = totalGames,
        startIndex = startIndex,
        endedIndex = endedIndex
    )
}