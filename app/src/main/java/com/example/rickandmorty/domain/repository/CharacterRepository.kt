package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.models.Character

interface CharacterRepository {

    suspend fun getCharacters(page: Int): Result<List<Character>>
    suspend fun getCharacterById(id: Int): Result<Character>
    suspend fun insertCharacter(character: Character): Result<Character>
    suspend fun deleteFavoriteCharacter(id: Int): Result<Character>
    suspend fun getCharacterByIdDao(id: Int): Result<Character>
    suspend fun updateFavorite(isFavorite: Boolean?, id: Int): Result<Character>
}