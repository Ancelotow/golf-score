package com.esgi.golf.data.repositories

import com.esgi.golf.domain.models.Game

class GameLocalRepository : GameRepository {

    private val games = mutableListOf<Game>()

    override fun add(game: Game): Int {
        games.add(game);
        return game.id
    }

    override fun getAll(): List<Game> {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Game {
        TODO("Not yet implemented")
    }

}