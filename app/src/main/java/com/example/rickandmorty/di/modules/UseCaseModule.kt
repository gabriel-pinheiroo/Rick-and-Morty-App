package com.example.rickandmorty.di.modules

import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.domain.repository.EpisodeRepository
import com.example.rickandmorty.domain.repository.LocationRepository
import com.example.rickandmorty.domain.use_cases.CharacterUseCase
import com.example.rickandmorty.domain.use_cases.EpisodeUseCase
import com.example.rickandmorty.domain.use_cases.LocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCharacterUseCase(
        characterRepository: CharacterRepository,
    ): CharacterUseCase = CharacterUseCase(characterRepository)

    @Provides
    fun provideEpisodeUseCase(
        episodeRepository: EpisodeRepository,
    ): EpisodeUseCase = EpisodeUseCase(episodeRepository)

    @Provides
    fun provideLocationUseCase(
        locationRepository: LocationRepository,
    ): LocationUseCase = LocationUseCase(locationRepository)
}