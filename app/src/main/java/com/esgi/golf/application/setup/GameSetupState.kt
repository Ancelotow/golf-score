package com.esgi.golf.application.setup

class GameSetupState (
    val status: GameSetupStatus = GameSetupStatus.Loading,
    val error: Throwable? = null,
    val gameID: Int? = null
        ) {

    companion object {
        fun initial() = GameSetupState(status = GameSetupStatus.Initial)

        fun loading() = GameSetupState(status = GameSetupStatus.Loading)

        fun error(error: Throwable) = GameSetupState(status = GameSetupStatus.Error, error = error)

        fun success(gameID: Int) = GameSetupState(status = GameSetupStatus.Success, gameID = gameID)
    }

}