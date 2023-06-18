package com.esgi.golf.data.mappers

import com.esgi.golf.data.dto.PlayerDto
import com.esgi.golf.domain.models.Player
import javax.inject.Inject

class PlayerMapper @Inject constructor() {

    fun map(player: PlayerDto): Player {
        return Player(
            id = player.id,
            name = player.name,
            firstname = player.firstname,
            scoreTotal = player.scoreTotal,
        )
    }

    fun map(player: Player): PlayerDto {
        return PlayerDto(
            id = player.id,
            name = player.name,
            firstname = player.firstname,
            scoreTotal = player.scoreTotal,
        )
    }

}