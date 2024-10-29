package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.models.Location

interface LocationRepository {

    suspend fun getLocations(): Result<List<Location>>
}