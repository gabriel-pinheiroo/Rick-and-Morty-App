package com.example.rickandmorty.network.repository

import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.network.service.CharacterService
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterService
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): Result<List<Character>> {
        return try {

            val result = api.getCharacters(pages = page)
                .results
                .orEmpty()
                .map { response ->
                    response
                        .toCharacter()
                }
            Result.success(result)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }

    override suspend fun getCharacterById(id: Int): Result<Character> {
        return try {
            val result = api.getCharacterById(id = id)
                .toCharacter()
            Result.success(result)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}