package com.esgi.golf.data.mappers

import com.esgi.golf.data.dto.GameDto
import com.esgi.golf.domain.models.Game
import javax.inject.Inject

class GameMapper @Inject constructor(
    private val playerMapper: PlayerMapper,
    private val roundMapper: RoundMapper,
    private val holeMapper: HoleMapper,
) {

    fun map(game: GameDto): Game {
        return Game(
            id = game.id,
            name = game.name,
            players = game.players.map { playerMapper.map(it) },
            rounds = game.rounds.map { roundMapper.map(it) },
            holes = game.holes.map { holeMapper.map(it) },
            winner = if (game.winner == null) null else playerMapper.map(game.winner),
            date = game.date,
        )
    }

    fun map(game: Game): GameDto {
        return GameDto(
            id = game.id,
            name = game.name,
            players = game.players.map { playerMapper.map(it) },
            rounds = game.rounds.map { roundMapper.map(it) },
            holes = game.holes.map { holeMapper.map(it) },
            winner = if (game.winner == null) null else playerMapper.map(game.winner!!),
            date = game.date,
        )
    }

}