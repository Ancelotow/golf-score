package com.esgi.golf.domain.builder

import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import java.time.LocalDate

interface GameBuilder {

    fun addPlayer(player: Player): GameBuilder

    fun addHole(hole: Hole): GameBuilder

    fun buildDate(date: LocalDate): GameBuilder

    fun buildName(name: String): GameBuilder

    fun buildWinner(winner: Player?): GameBuilder

    fun build(): Game

    fun reset(): GameBuilder

}