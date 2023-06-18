package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.repositories.GameRepository
import javax.inject.Inject

class GameSetupService @Inject constructor(
    private val repository: GameRepository
) : GameSetupServiceInterface {

    override fun addGame(game: Game): Int {
        return repository.add(game) ?: throw Exception("Game not added.")
    }

}