package com.esgi.golf.domain.builder

import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import com.esgi.golf.domain.models.Round
import com.esgi.golf.domain.models.ScoreType
import java.time.LocalDate
import javax.inject.Inject

class GameBuilderDefault @Inject constructor() : GameBuilder {

    private val players = mutableListOf<Player>()
    private val holes = mutableListOf<Hole>()
    private var name: String = ""
    private var winner: Player? = null
    private var date: LocalDate = LocalDate.now()

    override fun addPlayer(player: Player) = apply { players.add(player) }

    override fun addHole(hole: Hole) = apply { holes.add(hole) }

    override fun buildDate(date: LocalDate) = apply { this.date = date }

    override fun buildName(name: String) = apply { this.name = name }

    override fun buildWinner(winner: Player?) = apply { this.winner = winner }

    override fun reset() = apply {
        players.clear()
        holes.clear()
        name = ""
        winner = null
        date = LocalDate.now()
    }

    override fun build(): Game {
        val rounds = mutableListOf<Round>()
        players.forEach { player ->
            holes.forEach { hole ->
                rounds.add(Round(player, hole, 0, -1, players.indexOf(player) + 1, ScoreType.Unknown))
            }
        }
        val game = Game(
            0,
            name,
            players.toList(),
            holes.toList(),
            rounds,
            winner,
            date
        )
        players.clear()
        holes.clear()
        name = ""
        winner = null
        date = LocalDate.now()
        return game
    }

}