package com.esgi.golf.domain.repositories

import com.esgi.golf.domain.models.Game

interface GameRepository {

    fun add(game: Game): Int

    fun getAll(): List<Game>

    fun get(id: Int): Game?

    fun update(game: Game)
}