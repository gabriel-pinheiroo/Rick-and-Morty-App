package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.domain.models.Location
import com.example.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LocationUseCaseTest{

    private lateinit var useCase: LocationUseCase
    private lateinit var repository: LocationRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = mock(LocationRepository::class.java)
        useCase = LocationUseCase(repository)
    }

    @Test
    fun `getLocations should return success when repository call succeeds`() = runBlocking {
        val mockLocation = Location(id = 1, name = "Earth (C-137)")
        `when`(repository.getLocations(1)).thenReturn(Result.success(listOf(mockLocation)))

        val result = useCase.getLocations(1)
        assertTrue(result.isSuccess)
        assertEquals(listOf(mockLocation), result.getOrNull())
    }

    @Test
    fun `getLocations should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("API error")
        `when`(repository.getLocations(1)).thenReturn(Result.failure(exception))

        val result = useCase.getLocations(1)
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
