package com.example.rickandmorty.features.character

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
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CharacterState.Idle)
    val state = _state
        .onStart { getCharacters() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = CharacterState.Idle,
        )

    private fun getCharacters() {
        viewModelScope.launch {
            _state.update { it.onLoading() }
            try {
                val characters = characterUseCase.getCharacters().getOrThrow()
                _state.update { it.onCharactersLoaded(characters) }
            } catch (e: Throwable) {
                println("Could not get characters. ex: $e")
                _state.update { it.onLoadingFinished() }
            }finally {
                _state.update { it.onLoadingFinished() }
            }
        }
    }
}