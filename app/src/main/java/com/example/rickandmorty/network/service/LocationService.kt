package com.example.rickandmorty.network.service

import com.example.rickandmorty.network.dto.location.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int
    ): LocationsResponse
}