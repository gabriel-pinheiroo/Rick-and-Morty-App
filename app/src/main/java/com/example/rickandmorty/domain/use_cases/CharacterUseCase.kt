package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    suspend fun getCharacters() = characterRepository.getCharacters()
}