package com.esgi.golf.application.score

import com.esgi.golf.domain.models.Game

data class ScoreState(
    val status: ScoreStateStatus = ScoreStateStatus.Loading,
    val error: Throwable? = null,
    val game: Game? = null
) {
    companion object {
        fun initial() = ScoreState(status = ScoreStateStatus.Initial)

        fun loading() = ScoreState(status = ScoreStateStatus.Loading)

        fun error(error: Throwable) = ScoreState(status = ScoreStateStatus.Error, error = error)

        fun success(game: Game) = ScoreState(status = ScoreStateStatus.Success, game = game)
    }
}