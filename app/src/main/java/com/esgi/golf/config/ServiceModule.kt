package com.esgi.golf.config

import com.esgi.golf.domain.services.GameListService
import com.esgi.golf.domain.services.GameListServiceInterface
import com.esgi.golf.domain.services.GameSetupService
import com.esgi.golf.domain.services.GameSetupServiceInterface
import com.esgi.golf.domain.services.ScoreCalculatorService
import com.esgi.golf.domain.services.StrokePlayService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    @Singleton
    abstract fun provideCalculatorService(service: StrokePlayService): ScoreCalculatorService

    @Binds
    @Singleton
    abstract fun provideGameSetupService(service: GameSetupService): GameSetupServiceInterface

    @Binds
    @Singleton
    abstract fun provideGameListService(service: GameListService): GameListServiceInterface
}