package com.esgi.golf.data.repositories

import com.esgi.golf.domain.models.Game

interface GameRepository {

    fun add(game: Game): Int

    fun getAll(): List<Game>

    fun get(id: Int): Game?

}