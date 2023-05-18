package com.esgi.golf.data.repositories
import com.esgi.golf.domain.models.Game
import org.junit.Assert.assertEquals
import org.junit.Test

class GameLocalRepositoryTest {

    private val repository = GameLocalRepository()

    @Test
    fun addGame() {
        val game = Game(2, "Partie 2", listOf(), listOf(), listOf())
        val result = repository.add(game)
        assertEquals(game.id, result)
    }

    @Test
    fun getGame() {
        val newGame = Game(2, "Partie 2", listOf(), listOf(), listOf())
        val id = repository.add(newGame)
        val game = repository.get(id)
        assertEquals(newGame, game)
    }

}