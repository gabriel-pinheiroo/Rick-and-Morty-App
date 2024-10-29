package com.example.rickandmorty.network.service

import com.example.rickandmorty.network.dto.episodes.EpisodesResponse
import retrofit2.http.GET

interface EpisodeService {

    @GET("episode")
    suspend fun getEpisodes(): EpisodesResponse
}