package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.domain.models.Episode
import com.example.rickandmorty.domain.repository.EpisodeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class EpisodeUseCaseTest{

    private lateinit var useCase: EpisodeUseCase
    private lateinit var repository: EpisodeRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = mock(EpisodeRepository::class.java)
        useCase = EpisodeUseCase(repository)
    }

    @Test
    fun `getEpisodes should return success when repository call succeeds`() = runBlocking {
        val mockEpisode = Episode(id = 1, name = "Pilot", episode = "S01E01")
        `when`(repository.getEpisodes(1)).thenReturn(Result.success(listOf(mockEpisode)))

        val result = useCase.getEpisodes(1)
        assertTrue(result.isSuccess)
        assertEquals(listOf(mockEpisode), result.getOrNull())
    }

    @Test
    fun `getEpisodes should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("API error")
        `when`(repository.getEpisodes(1)).thenReturn(Result.failure(exception))

        val result = useCase.getEpisodes(1)
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
