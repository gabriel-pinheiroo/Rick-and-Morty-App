package com.example.rickandmorty.network.service

import com.example.rickandmorty.network.dto.location.LocationsResponse
import retrofit2.http.GET

interface LocationService {

    @GET("location")
    suspend fun getLocations(): LocationsResponse
}