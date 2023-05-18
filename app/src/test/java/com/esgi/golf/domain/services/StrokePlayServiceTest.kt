package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import org.junit.Assert.assertEquals
import org.junit.Test

class StrokePlayServiceTest {

    @Test
    fun addShotToPlayer(){
        val hole = Hole(1, "Trou 1", 2)
        val player = Player(1, "Jean", "DUPONT")
        val service = StrokePlayService()
        val result = service.addShot(hole, player)
        assertEquals(1, result)
    }

    @Test
    fun addMultipleShotToPlayer(){
        val hole = Hole(1, "Trou 1", 2)
        val player = Player(1, "Jean", "DUPONT")
        val service = StrokePlayService()
        service.addShot(hole, player)
        val result = service.addShot(hole, player)
        assertEquals(2, result)
    }

    @Test
    fun addShotToMultiplePlayer(){
        val hole = Hole(1, "Trou 1", 2)
        val playerOne = Player(1, "Jean", "DUPONT")
        val playerTwo = Player(2, "Patrick", "DUPONT")
        val service = StrokePlayService()
        val resultOne = service.addShot(hole, playerOne)
        val resultTwo = service.addShot(hole, playerTwo)
        assertEquals(1, resultOne)
        assertEquals(1, resultTwo)
    }

    @Test
    fun addMultipleShotToMultiplePlayer(){
        val hole = Hole(1, "Trou 1", 2)
        val playerOne = Player(1, "Jean", "DUPONT")
        val playerTwo = Player(2, "Patrick", "DUPONT")
        val service = StrokePlayService()
        service.addShot(hole, playerOne)
        val resultOne = service.addShot(hole, playerOne)
        service.addShot(hole, playerTwo)
        val resultTwo = service.addShot(hole, playerTwo)
        assertEquals(2, resultOne)
        assertEquals(2, resultTwo)
    }

}