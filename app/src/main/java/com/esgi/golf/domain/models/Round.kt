package com.esgi.golf.domain.models

data class Round(
    val player: Player,
    val hole: Hole,
    val nbShot: Int,
)
