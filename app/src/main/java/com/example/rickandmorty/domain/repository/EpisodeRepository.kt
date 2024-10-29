package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.models.Episode

interface EpisodeRepository {

    suspend fun getEpisodes(): Result<List<Episode>>
}