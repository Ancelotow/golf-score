package com.esgi.golf.data.data_source

import com.esgi.golf.data.dto.GameDto
import com.esgi.golf.data.dto.HoleDto
import com.esgi.golf.data.dto.PlayerDto
import com.esgi.golf.data.dto.RoundDto
import javax.inject.Inject

class LocalGameDataSource @Inject constructor(): GameDataSource {

    private val players = mutableListOf(
        PlayerDto(1, "John", "Doe", 0),
        PlayerDto(2, "Jane", "Doe", 0),
        PlayerDto(3, "Foo", "Bar", 0),
        PlayerDto(4, "Baz", "Qux", 0),
    )

    private val holes = mutableListOf(
        HoleDto(1, 1, "Hole 1", 10),
        HoleDto(2, 2, "Hole 2", 12),
        HoleDto(3, 3, "Hole 3", 14),
        HoleDto(4, 4, "Hole 4", 15),
        HoleDto(5, 5, "Hole 5", 12),
        HoleDto(6, 6, "Hole 6", 18),
        HoleDto(7, 7, "Hole 7", 10),
        HoleDto(8, 8, "Hole 8", 8),
        HoleDto(9, 9, "Hole 9", 14),
    )

    private val rounds = mutableListOf(
        RoundDto(players[0], holes[0], 3, 1, 0),
        RoundDto(players[0], holes[1], 4, 1, 0),
        RoundDto(players[0], holes[2], 5, 1, 0),
        RoundDto(players[0], holes[3], 3, 1, 0),
        RoundDto(players[0], holes[4], 4, 1, 0),
        RoundDto(players[0], holes[5], 5, 1, 0),
        RoundDto(players[0], holes[6], 3, 1, 0),
        RoundDto(players[0], holes[7], 4, 1, 0),
        RoundDto(players[0], holes[8], 5, 1, 0),
        RoundDto(players[1], holes[0], 3, 2, 0),
        RoundDto(players[1], holes[1], 4, 2, 0),
        RoundDto(players[1], holes[2], 5, 2, 0),
        RoundDto(players[1], holes[3], 3, 2, 0),
        RoundDto(players[1], holes[4], 4, 2, 0),
        RoundDto(players[1], holes[5], 5, 2, 0),
        RoundDto(players[1], holes[6], 3, 2, 0),
        RoundDto(players[1], holes[7], 4, 2, 0),
        RoundDto(players[1], holes[8], 5, 2, 0),
        RoundDto(players[2], holes[0], 3, 3, 0),
        RoundDto(players[2], holes[1], 4, 3, 0),
        RoundDto(players[2], holes[2], 5, 3, 0),
        RoundDto(players[2], holes[3], 3, 3, 0),
        RoundDto(players[2], holes[4], 4, 3, 0),
        RoundDto(players[2], holes[5], 5, 3, 0),
        RoundDto(players[2], holes[6], 3, 3, 0),
        RoundDto(players[2], holes[7], 4, 3, 0),
        RoundDto(players[2], holes[8], 5, 3, 0),
        RoundDto(players[3], holes[0], 3, 4, 0),
        RoundDto(players[3], holes[1], 4, 4, 0),
        RoundDto(players[3], holes[2], 5, 4, 0),
        RoundDto(players[3], holes[3], 3, 4, 0),
        RoundDto(players[3], holes[4], 4, 4, 0),
        RoundDto(players[3], holes[5], 5, 4, 0),
        RoundDto(players[3], holes[6], 3, 4, 0),
        RoundDto(players[3], holes[7], 4, 4, 0),
        RoundDto(players[3], holes[8], 5, 4, 0),
    )

    private val games = mutableListOf(
        GameDto(1, "Game 1", players, holes, rounds, players[0], java.time.LocalDate.now()),
    )

    override fun add(game: GameDto): Int {
        var id = 1
        games.maxBy { e -> e.id }.let { id = it.id + 1}
        games.add(game.copy(id = id))
        return id
    }

    override fun get(id: Int): GameDto? {
        return games.find { e -> e.id == id }
    }

    override fun getAll(): List<GameDto> {
        return games.toList()
    }

    override fun update(game: GameDto) {
        games.removeIf { e -> e.id == game.id }
        games.add(game)
    }

}