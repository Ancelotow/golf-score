package com.esgi.golf.domain.services

import com.esgi.golf.domain.exceptions.GameException
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.repositories.GameRepository
import javax.inject.Inject

class GameReportService @Inject constructor(private val repository: GameRepository) :
    GameReportServiceInterface {
    override fun getGame(id: Int): Game {
        return repository.get(id) ?: throw GameException("This game does not exist.")
    }

    override fun getTotalNbShots(): Int {
        TODO("Not yet implemented")
    }
}