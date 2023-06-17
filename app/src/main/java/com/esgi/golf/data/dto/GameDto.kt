package com.esgi.golf.data.dto

data class GameDto(
    val id: Int,
    val name: String,
    val players: List<PlayerDto>,
    val holes: List<HoleDto>,
    val rounds: List<RoundDto>,
) {



}
