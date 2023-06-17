package com.esgi.golf.domain.models

import java.time.LocalDate

data class Game(
    val id: Int,
    val name: String,
    val players: List<Player>,
    val holes: List<Hole>,
    val rounds: List<Round>,
    val winner: Player,
    val date: LocalDate
)