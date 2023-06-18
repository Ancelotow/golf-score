package com.esgi.golf.data.mappers

import com.esgi.golf.data.dto.RoundDto
import com.esgi.golf.domain.models.Round
import javax.inject.Inject

class RoundMapper @Inject constructor(
    private val playerMapper: PlayerMapper,
    private val holeMapper: HoleMapper,
) {

    fun map(round: RoundDto): Round {
        return Round(
            player = playerMapper.map(round.player),
            hole = holeMapper.map(round.hole),
            nbShot = round.nbShot,
            order = round.order,
            score = round.score,
        )
    }

    fun map(round: Round): RoundDto {
        return RoundDto(
            player = playerMapper.map(round.player),
            hole = holeMapper.map(round.hole),
            nbShot = round.nbShot,
            order = round.order,
            score = round.score,
        )
    }
}