package com.esgi.golf.data.dto

import com.esgi.golf.domain.models.ScoreType

data class RoundDto(
    val player: PlayerDto,
    val hole: HoleDto,
    var nbShot: Int,
    val order: Int,
    var score: Int,
    var type: Int = -1
)