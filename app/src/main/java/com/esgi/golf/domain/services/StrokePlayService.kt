package com.esgi.golf.domain.services

import com.esgi.golf.data.repositories.GameRepository
import com.esgi.golf.domain.exceptions.GameException
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player

class StrokePlayService(
    private val gameId: Int,
    private val repository: GameRepository
) : ScoreCalculatorService {

    override fun addShot(hole: Hole, player: Player): Int {
        val game = getGame()
        val round = game.rounds.find { r -> hole.id == r.hole.id && player.id == r.player.id }
            ?: throw GameException("this round does not exist")
        return ++round.nbShot
    }

    override fun getWinner(): Player {
        val game = getGame()
        val scores = HashMap<Player, Int>()
        for(round in game.rounds) {
            if(scores[round.player] == null) {
                scores[round.player] = 0;
            }
            scores[round.player] = scores[round.player]!! + round.nbShot
        }

        return scores.maxBy{ it.value }.key
    }

    private fun getGame(): Game {
        return repository.get(gameId) ?: throw GameException("this game does not exist")
    }

}