package com.example.rickandmorty.network.repository

import com.example.rickandmorty.domain.models.Location
import com.example.rickandmorty.domain.repository.LocationRepository
import com.example.rickandmorty.network.service.LocationService
import retrofit2.HttpException
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: LocationService
): LocationRepository {
    override suspend fun getLocations(): Result<List<Location>> {
        return try {

            val result = api.getLocations()
                .results
                .orEmpty()
                .map { response ->
                    response
                        .toLocation()
                }
            Result.success(result)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}