package com.esgi.golf.domain.models

data class Hole(
    val id: Int,
    val order: Int,
    val name: String,
    val par: Int,
) {
    override fun toString(): String {
        return "$name (Par: $par)"
    }
}
