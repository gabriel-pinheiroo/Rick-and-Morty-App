package com.example.rickandmorty.di.modules

import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.domain.repository.EpisodeRepository
import com.example.rickandmorty.domain.repository.LocationRepository
import com.example.rickandmorty.network.repository.CharacterRepositoryImpl
import com.example.rickandmorty.network.repository.EpisodeRepositoryImpl
import com.example.rickandmorty.network.repository.LocationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    internal abstract fun bindsCharacterRepository(
        characterRepository: CharacterRepositoryImpl,
    ): CharacterRepository

    @Binds
    @Singleton
    internal abstract fun bindsEpisodeRepository(
        episodeRepository: EpisodeRepositoryImpl,
    ): EpisodeRepository

    @Binds
    @Singleton
    internal abstract fun bindsLocationRepository(
        locationRepository: LocationRepositoryImpl,
    ): LocationRepository
}