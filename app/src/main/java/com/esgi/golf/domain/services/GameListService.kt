package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.repositories.GameRepository
import javax.inject.Inject

class GameListService @Inject constructor(
    private val repository: GameRepository
) : GameListServiceInterface{

    override fun getGames(): List<Game> {
        return repository.getAll() ?: throw Exception("Games not found.")
    }

}