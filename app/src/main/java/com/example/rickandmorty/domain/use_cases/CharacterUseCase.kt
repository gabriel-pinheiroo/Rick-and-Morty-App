package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    suspend fun getCharacters(page: Int) = characterRepository.getCharacters(page = page)
    suspend fun getCharacterById(id: Int) = characterRepository.getCharacterById(id = id)
    suspend fun insertCharacter(character: Character) = characterRepository.insertCharacter(character = character)
    suspend fun deleteFavoriteCharacter(id: Int) = characterRepository.deleteFavoriteCharacter(id = id)
    suspend fun getCharacterByIdDao(id: Int) = characterRepository.getCharacterByIdDao(id = id)
    suspend fun updateFavorite(character: Character) = characterRepository.updateFavorite(isFavorite = character.isFavorite, id = character.id)
    suspend fun getAllFavoriteCharacters() = characterRepository.getAllFavoriteCharacters()
}