package com.example.rickandmorty.network.service

import com.example.rickandmorty.network.dto.episodes.EpisodesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeService {

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): EpisodesResponse
}