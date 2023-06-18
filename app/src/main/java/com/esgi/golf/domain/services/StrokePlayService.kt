package com.esgi.golf.domain.services

import android.util.Log
import com.esgi.golf.domain.repositories.GameRepository
import com.esgi.golf.domain.exceptions.GameException
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import javax.inject.Inject

class StrokePlayService @Inject constructor(
    private val repository: GameRepository
) : ScoreCalculatorService {

    override fun getGame(id: Int): Game {
        return repository.get(id) ?: throw GameException("this game does not exist")
    }

    override fun addShot(hole: Hole, player: Player, gameId: Int): Int {
        val game = repository.get(gameId) ?: throw GameException("this game does not exist")
        val round = game.rounds.find { r -> hole.id == r.hole.id && player.id == r.player.id }
            ?: throw GameException("this round does not exist")
        round.nbShot += 1

        round.score = getScore(hole.par, round.nbShot)
        repository.update(game)
        return round.nbShot
    }

    override fun removeShot(hole: Hole, player: Player, gameId: Int): Int {
        val game = repository.get(gameId) ?: throw GameException("this game does not exist")
        val round = game.rounds.find { r -> hole.id == r.hole.id && player.id == r.player.id }
            ?: throw GameException("this round does not exist")
        if (round.nbShot > 0) {
            round.nbShot -= 1
        }

        round.score = getScore(hole.par, round.nbShot)
        repository.update(game)
        return round.nbShot
    }

    private fun getScore(par: Int, nbShot: Int): Int {
        return if (nbShot < par) {
            -1
        } else {
            nbShot - par
        }
    }

    override fun finishGame(game: Game) {
        for (index in 0 until game.players.size) {
            game.players[index].scoreTotal = game.rounds.filter {
                round -> round.player.id == game.players[index].id
            }.sumOf {
                round -> round.score
            }
        }
        game.winner = game.players.minByOrNull { player -> player.scoreTotal }
        repository.update(game)
    }

}