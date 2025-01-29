package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.models.Character

interface CharacterRepository {

    suspend fun getCharacters(page: Int): Result<List<Character>>
    suspend fun getCharacterById(id: Int): Result<Character>
}