package com.example.rickandmorty.features.favoriteCharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.use_cases.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCharactersViewModel @Inject constructor (
    private val useCase: CharacterUseCase
): ViewModel() {

    private val _state = MutableStateFlow(FavoriteCharactersState())
    val state = _state
        .onStart { getFavoriteCharacters() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = FavoriteCharactersState.IDLE,
        )

    private fun getFavoriteCharacters() {
        _state.update { it.onLoading() }
        viewModelScope.launch {
            try {
                val favoriteCharacters = useCase.getAllFavoriteCharacters().getOrThrow()
                _state.update { it.onLoadFavoriteCharacters(favoriteCharacters) }
            } catch (e: Exception) {
                println("Error in getting favorite characters: ${e.message}")
                _state.update { it.onLoadingFinished() }
            }
        }
    }
}