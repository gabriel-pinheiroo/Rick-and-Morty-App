package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.models.Character

interface CharacterRepository {

    suspend fun getCharacters(): Result<List<Character>>
}