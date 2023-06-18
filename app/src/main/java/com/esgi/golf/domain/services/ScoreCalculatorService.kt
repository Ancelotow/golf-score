package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player

interface ScoreCalculatorService {
    fun getGame(id: Int): Game

    fun addShot(hole: Hole, player: Player, gameId: Int): Int

    fun removeShot(hole: Hole, player: Player, gameId: Int): Int

    fun finishGame(game: Game)
}