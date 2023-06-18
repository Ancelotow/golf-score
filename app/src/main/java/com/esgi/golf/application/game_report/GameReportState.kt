package com.esgi.golf.application.game_report

import com.esgi.golf.domain.models.Game

data class GameReportState(
    val status: GameReportStatus = GameReportStatus.Loading,
    val error: Throwable? = null,
    val game: Game? = null,
) {
    companion object {
        fun loading() = GameReportState(status = GameReportStatus.Loading)

        fun error(error: Throwable) =
            GameReportState(status = GameReportStatus.Error, error = error)

        fun success(game: Game) =
            GameReportState(status = GameReportStatus.Success, game = game)
    }
}
