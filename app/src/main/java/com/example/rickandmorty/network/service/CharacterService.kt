package com.example.rickandmorty.network.service

import com.example.rickandmorty.network.dto.character.CharactersResponse
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}