package com.esgi.golf.domain.services

import com.esgi.golf.domain.exceptions.GameException
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.repositories.GameRepository
import javax.inject.Inject

class GameReportService @Inject constructor(private val repository: GameRepository) :
    GameReportServiceInterface {

    private val gameId: Int = 1

    override fun getGame(): Game {
        return repository.get(gameId) ?: throw GameException("This game does not exist.")
    }

    override fun getTotalNbShots(): Int {
        TODO("Not yet implemented")
    }
}