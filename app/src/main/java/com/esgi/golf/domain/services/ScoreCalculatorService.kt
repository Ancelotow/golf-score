package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player

interface ScoreCalculatorService {
    fun getGame(): Game

    fun addShot(hole: Hole, player: Player): Int

    fun removeShot(hole: Hole, player: Player): Int

    fun getWinner(): Player

    fun getTypeGame(): String
}