package com.esgi.golf.data.repositories
import com.esgi.golf.domain.models.Game
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
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
    fun getExistGame() {
        val newGame = Game(2, "Partie 2", listOf(), listOf(), listOf())
        val id = repository.add(newGame)
        val game = repository.get(id)
        assertEquals(newGame, game)
    }

    @Test
    fun getNotExistGame() {
        val game = repository.get(0)
        assertNull(game)
    }

    @Test
    fun getAllGames() {
        val gameOne = Game(1, "Partie 1", listOf(), listOf(), listOf())
        val gameTwo = Game(2, "Partie 2", listOf(), listOf(), listOf())
        repository.add(gameOne)
        repository.add(gameTwo)
        val games = repository.getAll()
        assertEquals(2, games.count())
    }

}