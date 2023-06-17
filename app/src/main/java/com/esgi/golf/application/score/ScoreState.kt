package com.esgi.golf.application.score

import com.esgi.golf.domain.models.Game

data class ScoreState(
    val status: ScoreStateStatus = ScoreStateStatus.Loading,
    val error: Throwable? = null,
    val game: Game? = null,
    val type: String
) {
    companion object {
        fun initial(type: String) = ScoreState(status = ScoreStateStatus.Initial, type = type)

        fun loading(type: String) = ScoreState(status = ScoreStateStatus.Loading, type = type)

        fun error(error: Throwable, type: String) = ScoreState(status = ScoreStateStatus.Error, type = type, error = error)

        fun success(game: Game, type: String) = ScoreState(status = ScoreStateStatus.Success, type = type, game = game)
    }
}