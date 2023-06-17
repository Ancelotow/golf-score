package com.esgi.golf.data.dto

data class HoleDto(
    val id: Int,
    val order: Int,
    val name: String,
    val par: Int,
) {
    override fun toString(): String {
        return name
    }
}