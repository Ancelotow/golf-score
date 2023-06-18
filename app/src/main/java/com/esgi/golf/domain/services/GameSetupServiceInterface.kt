package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Game

interface GameSetupServiceInterface {

    fun addGame(game: Game): Int

}