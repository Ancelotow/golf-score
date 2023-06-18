package com.esgi.golf.application.score.score_state

import com.esgi.golf.domain.models.Round

data class ScoreState(
    val status: ScoreStateStatus = ScoreStateStatus.Loading,
    val error: Throwable? = null,
    val rounds: List<Round> = mutableListOf(),
) {
    companion object {
        fun initial() = ScoreState(status = ScoreStateStatus.Initial)

        fun loading() = ScoreState(status = ScoreStateStatus.Loading)

        fun error(error: Throwable) = ScoreState(status = ScoreStateStatus.Error, error = error)

        fun success(rounds: List<Round>) = ScoreState(status = ScoreStateStatus.Success, rounds = rounds)
    }
}