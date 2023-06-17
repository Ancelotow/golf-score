package com.esgi.golf.data.repositories

import com.esgi.golf.data.data_source.GameDataSource
import com.esgi.golf.data.mappers.GameMapper
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.repositories.GameRepository
import javax.inject.Inject

class GameLocalRepository @Inject constructor(
    private val dataSource: GameDataSource,
    private val gameMapper: GameMapper
) : GameRepository {

    override fun add(game: Game): Int {
        return dataSource.add(gameMapper.map(game))
    }

    override fun get(id: Int): Game? {
        return dataSource.get(id)?.let { gameMapper.map(it) }
    }

    override fun getAll(): List<Game> {
        return dataSource.getAll().map { gameMapper.map(it) }
    }

}