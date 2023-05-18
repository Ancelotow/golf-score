package com.esgi.golf.domain.services

import com.esgi.golf.data.repositories.GameRepository
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import com.esgi.golf.domain.models.Round
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class StrokePlayServiceTest {

    val repository = mock(GameRepository::class.java)
    private lateinit var game: Game
    private lateinit var playerOne: Player
    private lateinit var playerTwo: Player
    private lateinit var roundOne: Round
    private lateinit var roundTwo: Round
    private lateinit var hole: Hole

    @Before
    fun init(){
        hole = Hole(1, 1, "Trou 1", 2)
        playerOne = Player(1, "Jean", "DUPONT")
        playerTwo = Player(2, "Jean", "DUPONT")
        roundOne = Round(playerOne, hole, 0, 1)
        roundTwo = Round(playerTwo, hole, 0, 2)
        game = Game(1, "Jeu 1", listOf(playerOne, playerTwo), listOf(hole), listOf(roundOne, roundTwo))
    }

    @Test
    fun addShotToPlayer(){
        `when`(repository.get(game.id)).thenReturn(game)
        val service = StrokePlayService(game.id, repository)
        val result = service.addShot(hole, playerOne)
        assertEquals(1, result)
    }

    @Test
    fun addMultipleShotToPlayer(){
        `when`(repository.get(game.id)).thenReturn(game)
        val service = StrokePlayService(game.id, repository)
        service.addShot(hole, playerOne)
        val result = service.addShot(hole, playerOne)
        assertEquals(2, result)
    }

    @Test
    fun addShotToMultiplePlayer(){
        `when`(repository.get(game.id)).thenReturn(game)
        val service = StrokePlayService(game.id, repository)
        val resultOne = service.addShot(hole, playerOne)
        val resultTwo = service.addShot(hole, playerTwo)
        assertEquals(1, resultOne)
        assertEquals(1, resultTwo)
    }

    @Test
    fun addMultipleShotToMultiplePlayer(){
        `when`(repository.get(game.id)).thenReturn(game)
        val service = StrokePlayService(game.id, repository)
        service.addShot(hole, playerOne)
        val resultOne = service.addShot(hole, playerOne)
        service.addShot(hole, playerTwo)
        val resultTwo = service.addShot(hole, playerTwo)
        assertEquals(2, resultOne)
        assertEquals(2, resultTwo)
    }

    @Test
    fun getWinner() {
        `when`(repository.get(game.id)).thenReturn(game)
        val service = StrokePlayService(game.id, repository)
        service.addShot(hole, playerOne)
        service.addShot(hole, playerOne)
        service.addShot(hole, playerTwo)
        val winner = service.getWinner()
        assertEquals(playerOne, winner)
    }

}