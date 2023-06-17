package com.esgi.golf.data.data_source

import com.esgi.golf.data.dto.GameDto

interface GameDataSource {

    fun add(game: GameDto): Int

    fun getAll(): List<GameDto>

    fun get(id: Int): GameDto?

    fun update(game: GameDto)
}