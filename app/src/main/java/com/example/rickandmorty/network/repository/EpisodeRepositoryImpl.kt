package com.example.rickandmorty.network.repository

import com.example.rickandmorty.domain.models.Episode
import com.example.rickandmorty.domain.repository.EpisodeRepository
import com.example.rickandmorty.network.service.EpisodeService
import retrofit2.HttpException
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val api: EpisodeService
): EpisodeRepository {
    override suspend fun getEpisodes(page: Int): Result<List<Episode>> {
        return try {

            val result = api.getEpisodes(page = page)
                .results
                .orEmpty()
                .map { response ->
                    response
                        .toEpisode()
                }
            Result.success(result)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}