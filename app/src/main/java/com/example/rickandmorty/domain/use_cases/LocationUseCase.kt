package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.domain.repository.LocationRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend fun getLocations() = locationRepository.getLocations()
}