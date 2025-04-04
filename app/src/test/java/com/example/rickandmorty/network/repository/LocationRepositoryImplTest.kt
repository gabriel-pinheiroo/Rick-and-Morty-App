package com.example.rickandmorty.network.repository

import com.example.rickandmorty.domain.models.Location
import com.example.rickandmorty.network.dto.location.LocationsResponse
import com.example.rickandmorty.network.dto.location.Result
import com.example.rickandmorty.network.service.LocationService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class LocationRepositoryImplTest{
    private lateinit var repository: LocationRepositoryImpl

    private val api: LocationService = mock(LocationService::class.java)

    @Before
    fun setup() {
        repository = LocationRepositoryImpl(api)
    }

    @Test
    fun `getLocations should return success when API call is successful`() = runBlocking {
        val mockLocation = Result(id = 1, name = "Earth C-137")
        val response = LocationsResponse(results = listOf(mockLocation))
        `when`(api.getLocations(1)).thenReturn(response)

        val result = repository.getLocations(1)
        assertTrue(result.isSuccess)
        val expectedResponse = response.results?.map { it.toLocation() } ?: emptyList()
        assertEquals(expectedResponse, result.getOrNull())
    }

    @Test
    fun `getLocations should return failure when API call fails`() = runBlocking {
        `when`(api.getLocations(1)).thenThrow(HttpException::class.java)

        val result = repository.getLocations(1)
        assertTrue(result.isFailure)
    }

    @Test
    fun `getLocations should return empty list when API response is null`() = runBlocking {
        val emptyResponse = LocationsResponse(results = null)
        `when`(api.getLocations(1)).thenReturn(emptyResponse)

        val result = repository.getLocations(1)
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Location>(), result.getOrNull())
    }
}
