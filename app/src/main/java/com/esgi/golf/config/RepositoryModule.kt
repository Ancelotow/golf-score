package com.esgi.golf.config

import com.esgi.golf.data.repositories.GameLocalRepository
import com.esgi.golf.domain.repositories.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideGameRepository(repository: GameLocalRepository): GameRepository

}