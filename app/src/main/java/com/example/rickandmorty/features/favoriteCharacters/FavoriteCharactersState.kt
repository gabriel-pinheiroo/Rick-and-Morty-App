package com.example.rickandmorty.features.favoriteCharacters

import com.example.rickandmorty.domain.models.Character

data class FavoriteCharactersState (
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val favoriteCharacters: List<Character> = emptyList(),
) {
    fun onLoading() = copy(isLoading = true, hasError = false)
    fun onLoadingFinished() = copy(isLoading = false)
    fun onLoadFavoriteCharacters(favoriteCharacters: List<Character>) = copy(favoriteCharacters = favoriteCharacters)

    companion object {
        val IDLE = FavoriteCharactersState()
    }
}