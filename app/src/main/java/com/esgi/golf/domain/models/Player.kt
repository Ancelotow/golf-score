package com.esgi.golf.domain.models

data class Player(
    val id: Int,
    val firstname: String,
    val name: String,
    var scoreTotal: Int,
)
