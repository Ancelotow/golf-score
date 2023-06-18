package com.esgi.golf.application.game

import com.esgi.golf.domain.models.Game

class GameListState(
    val status: GameListStatus = GameListStatus.Loading,
    val error: Throwable? = null,
    val games: List<Game>? = null
) {
    companion object {
        fun initial() = GameListState(status = GameListStatus.Initial)

        fun loading() = GameListState(status = GameListStatus.Loading)

        fun error(error: Throwable) = GameListState(status = GameListStatus.Error, error = error)

        fun success(games: List<Game>) = GameListState(status = GameListStatus.Success, games = games)
    }

}