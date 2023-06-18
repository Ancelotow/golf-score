package com.esgi.golf.domain.services

import com.esgi.golf.domain.models.Game

interface GameReportServiceInterface {
    fun getGame(): Game

    fun getTotalNbShots(): Int
}