package com.esgi.golf.data.repositories
import com.esgi.golf.domain.models.Game
import org.junit.Assert.assertEquals
import org.junit.Test

class GameLocalRepositoryTest {

    @Test
    fun addGame() {
        val repository = GameLocalRepository()
        val game = Game(1, "Partie 1", listOf(), listOf(), listOf())
        val result = repository.add(game)
        assertEquals(game.id, result)
    }

}