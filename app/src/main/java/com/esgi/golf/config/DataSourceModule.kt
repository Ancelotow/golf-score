package com.esgi.golf.config

import com.esgi.golf.data.data_source.GameDataSource
import com.esgi.golf.data.data_source.LocalGameDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideGameDataSource(dataSource: LocalGameDataSource): GameDataSource

}