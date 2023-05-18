package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player

class MatchPlayService : ScoreCalculatorService {

    override fun addShot(hole: Hole, player: Player): Int {
        return 0
    }

    override fun getWinner(): Player {
        return Player(0, "", "")
    }

}