package com.example.rickandmorty.features.character

import com.example.rickandmorty.domain.models.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val characters: List<Character> = emptyList(),
) {
    fun onLoading() = copy(isLoading = true, hasError = false)
    fun onLoadingFinished() = copy(isLoading = false)
    fun onCharactersLoaded(characters: List<Character>) = copy(characters = characters)

    companion object {
        internal val Idle = CharacterState()
    }
}