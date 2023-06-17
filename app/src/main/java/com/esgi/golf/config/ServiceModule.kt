package com.esgi.golf.config

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

}