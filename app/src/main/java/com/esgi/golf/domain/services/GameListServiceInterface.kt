package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Game

interface GameListServiceInterface {

    fun getGames(): List<Game>
}