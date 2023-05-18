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

}