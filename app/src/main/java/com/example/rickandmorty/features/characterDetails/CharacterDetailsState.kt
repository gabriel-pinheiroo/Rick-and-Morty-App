package com.example.rickandmorty.features.characterDetails

import com.example.rickandmorty.domain.models.Character

data class CharacterDetailsState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val character: Character = Character.EMPTY,
) {
    fun onLoading() = copy(isLoading = true, hasError = false)
    fun onLoadingFinished() = copy(isLoading = false)
    fun onLoadCharacter(character: Character) = copy(character = character)

    companion object {
        val IDLE = CharacterDetailsState()
    }
}