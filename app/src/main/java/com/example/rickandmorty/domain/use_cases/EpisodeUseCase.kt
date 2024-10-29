package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.domain.repository.EpisodeRepository
import javax.inject.Inject

class EpisodeUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository
) {

    suspend fun getEpisodes() = episodeRepository.getEpisodes()
}