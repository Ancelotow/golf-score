package com.esgi.golf.config

import com.esgi.golf.domain.builder.GameBuilder
import com.esgi.golf.domain.builder.GameBuilderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BuilderModule {

    @Binds
    abstract fun provideGameBuilder(builder: GameBuilderImpl): GameBuilder

}