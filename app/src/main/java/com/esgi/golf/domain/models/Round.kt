package com.esgi.golf.domain.models

data class Round(
    val player: Player,
    val hole: Hole,
    var nbShot: Int,
    var score: Int,
    var order: Int,
    var type: ScoreType
)
