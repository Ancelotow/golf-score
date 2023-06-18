package com.esgi.golf.application.score.game_state

import com.esgi.golf.domain.models.Game

class GameState(
    val status: GameStateStatus = GameStateStatus.Loading,
    val error: Throwable? = null,
    val game: Game? = null
) {
    companion object {
        fun initial() = GameState(status = GameStateStatus.Initial)

        fun loading() = GameState(status = GameStateStatus.Loading)

        fun error(error: Throwable) = GameState(status = GameStateStatus.Error, error = error)

        fun success(game: Game) = GameState(status = GameStateStatus.Success, game = game)
    }
}