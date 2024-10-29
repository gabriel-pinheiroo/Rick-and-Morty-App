package com.example.rickandmorty.features.location

import com.example.rickandmorty.domain.models.Location

data class LocationState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val locations: List<Location> = emptyList(),
) {
    fun onLoading() = copy(isLoading = true, hasError = false)
    fun onLoadingFinished() = copy(isLoading = false)
    fun onLocationsLoaded(locations: List<Location>) = copy(locations = locations)

    companion object {
        internal val Idle = LocationState()
    }
}
