package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Game

interface GameReportServiceInterface {
    fun getGame(id: Int): Game

    fun getTotalNbShots(): Int
}