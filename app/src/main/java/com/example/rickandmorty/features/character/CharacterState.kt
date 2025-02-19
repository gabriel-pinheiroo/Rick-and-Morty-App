package com.example.rickandmorty.features.character

import com.example.rickandmorty.domain.models.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val isPaginating: Boolean = false,
    val characters: List<Character> = emptyList(),
) {
    fun onLoading() = copy(isLoading = true, hasError = false)
    fun onLoadingFinished() = copy(isLoading = false)
    fun onPaginating() = copy(isPaginating = true)
    fun onCharactersLoaded(characters: List<Character>) = copy(characters = characters, isLoading = false)

    companion object {
        internal val Idle = CharacterState()
    }
}