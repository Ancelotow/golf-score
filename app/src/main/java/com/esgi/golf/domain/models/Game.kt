package com.esgi.golf.domain.models

data class Game(
    val id: Int,
    val name: String,
    val players: List<Player>,
    val holes: List<Hole>,
    val rounds: List<Round>,
)