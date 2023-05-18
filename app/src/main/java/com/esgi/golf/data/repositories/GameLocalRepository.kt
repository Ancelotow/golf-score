package com.esgi.golf.data.repositories

import com.esgi.golf.domain.models.Game

class GameLocalRepository : GameRepository {

    private val games = mutableListOf<Game>()

    override fun add(game: Game): Int {
        games.add(game);
        return game.id
    }

    override fun get(id: Int): Game? {
        return games.find { e -> e.id == id }
    }

    override fun getAll(): List<Game> {
        return games.toList()
    }

}