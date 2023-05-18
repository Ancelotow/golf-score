package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player

class StrokePlayService : ScoreCalculatorService {

    override fun addShot(hole: Hole, player: Player): Int {
        return ++player.nbShot
    }

    override fun getWinner(): Player {
        return Player(0, "", "")
    }

}