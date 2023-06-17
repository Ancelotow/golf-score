package com.esgi.golf.data.dto

import java.time.LocalDate

data class GameDto(
    val id: Int,
    val name: String,
    val players: List<PlayerDto>,
    val holes: List<HoleDto>,
    val rounds: List<RoundDto>,
    val winner: PlayerDto,
    val date: LocalDate
)
