package com.example.rickandmorty.network.service

import com.example.rickandmorty.network.dto.character.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") pages: Int
    ): CharactersResponse
}