package com.esgi.golf.data.dto

data class RoundDto(
    val player: PlayerDto,
    val hole: HoleDto,
    var nbShot: Int,
    val order: Int,
)