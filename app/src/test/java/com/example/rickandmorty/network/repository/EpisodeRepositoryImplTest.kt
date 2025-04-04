package com.example.rickandmorty.network.repository

import com.example.rickandmorty.domain.models.Episode
import com.example.rickandmorty.network.dto.episodes.EpisodesResponse
import com.example.rickandmorty.network.dto.episodes.Result
import com.example.rickandmorty.network.service.EpisodeService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class EpisodeRepositoryImplTest{

    @Mock
    private lateinit var api: EpisodeService

    @Mock
    private lateinit var repository: EpisodeRepositoryImpl

    @Before
    fun setup() {
        repository = EpisodeRepositoryImpl(api)
    }

    @Test
    fun `getEpisodes should return success when API call is successful`() = runBlocking {
        val mockEpisode = EpisodesResponse (info = null, results = listOf( Result(id = 1, name = "Pilot")))
        `when`(api.getEpisodes(1)).thenReturn(mockEpisode)

        val result = repository.getEpisodes(1)
        assertTrue(result.isSuccess)
        val expectedResponse = mockEpisode.results?.map { it.toEpisode() } ?: emptyList()
        assertEquals(expectedResponse, result.getOrNull())
    }

    @Test
    fun `getEpisodes should return failure when API call fails`() = runBlocking {
        `when`(api.getEpisodes(1)).thenThrow(HttpException::class.java)

        val result = repository.getEpisodes(1)

        assertTrue(result.isFailure)
    }

    @Test
    fun `getEpisodes should return empty list when API response has null results`() = runBlocking {
        val emptyResponse = EpisodesResponse(results = null)
        `when`(api.getEpisodes(1)).thenReturn(emptyResponse)

        val result = repository.getEpisodes(1)
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Episode>(), result.getOrNull())
    }
}
