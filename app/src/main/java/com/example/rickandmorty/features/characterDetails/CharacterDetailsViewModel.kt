package com.example.rickandmorty.features.characterDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.use_cases.CharacterUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CharacterDetailsViewModel.Factory::class)
class CharacterDetailsViewModel @AssistedInject constructor(
    private val useCase: CharacterUseCase,
    @Assisted private val characterId: Int
): ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailsState.IDLE)
    val state = _state
        .onStart { getCharacterDetails() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = CharacterDetailsState.IDLE,
        )

    @AssistedFactory
    interface Factory {
        fun create(
            characterId: Int
        ): CharacterDetailsViewModel
    }

    private fun getCharacterDetails() {
        _state.update { it.onLoading() }
        viewModelScope.launch {
            try {
                val character = useCase.getCharacterById(characterId).getOrThrow()
                _state.update { it.onLoadCharacter(character) }
            } catch (e: Throwable) {
                println("Could not get character. ex: $e")
            } finally {
                _state.update { it.onLoadingFinished() }
            }
        }
    }
}