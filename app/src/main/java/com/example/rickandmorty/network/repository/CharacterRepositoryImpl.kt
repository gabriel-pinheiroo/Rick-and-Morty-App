package com.example.rickandmorty.network.repository

import com.example.rickandmorty.database.CharacterDao
import com.example.rickandmorty.database.CharacterEntity
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.network.service.CharacterService
import retrofit2.HttpException
import java.sql.SQLException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterService,
    private val characterDao: CharacterDao
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

    override suspend fun insertCharacter(character: Character): Result<Character> {
        return try {
            val characterEntity = CharacterEntity.fromCharacter(character)
            characterDao.insertCharacters(characters = characterEntity)
            Result.success(character)
        } catch (e: SQLException) {
            Result.failure(e)
        }
    }

    override suspend fun deleteFavoriteCharacter(id: Int): Result<Character> {
        return try {
            characterDao.deleteCharacterById(id = id)
            Result.success(Character.EMPTY)
        } catch (e: SQLException) {
            Result.failure(e)
        }
    }

    override suspend fun getCharacterByIdDao(id: Int): Result<Character> {
        return try {
            val characterEntity = characterDao.getCharacterByIdDao(id = id)

            if (characterEntity != null) {
                Result.success(characterEntity.toCharacter())
            } else {
                Result.failure(NoSuchElementException(id.toString()))
            }
        } catch (e: SQLException) {
            Result.failure(e)
        }
    }

    override suspend fun updateFavorite(isFavorite: Boolean?, id: Int): Result<Character> {
        return try {
            characterDao.updateFavorite(isFavorite = isFavorite, id = id)
            Result.success(Character.EMPTY)
        } catch (e: SQLException) {
            Result.failure(e)
        }
    }
}