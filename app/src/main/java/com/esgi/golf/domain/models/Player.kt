package com.esgi.golf.domain.models

data class Player(
    val order: Int,
    val firstname: String,
    val name: String,
    var nbShot: Int = 0
)
