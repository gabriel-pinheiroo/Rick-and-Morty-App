package com.example.rickandmorty.features.characterDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.database.CharacterDao
import com.example.rickandmorty.domain.models.Character
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
    private val characterDao: CharacterDao,
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
                val localCharacter = characterDao.getCharacterById(characterId)
                if (localCharacter != null) {
                    _state.update { it.onLoadCharacter(localCharacter) }
                    return@launch
                }
                val character = useCase.getCharacterById(characterId).getOrThrow()
                _state.update { it.onLoadCharacter(character) }
            } catch (e: Throwable) {
                println("Could not get character. ex: $e")
            } finally {
                _state.update { it.onLoadingFinished() }
            }
        }
    }


    fun insertCharacter(character: Character) {
        viewModelScope.launch {
            characterDao.insertCharacters(character)
        }
    }

    fun updateFavorite(id: Int) {
        viewModelScope.launch {
            val currentCharacter = state.value.character
            val newFavoriteStatus = !currentCharacter.isFavorite

            if (newFavoriteStatus) {
                characterDao.insertCharacters(currentCharacter.copy(isFavorite = true))
            } else {
                characterDao.deleteCharacterById(id)
            }

            _state.update { currentState ->
                currentState.copy(
                    character = currentState.character.copy(isFavorite = newFavoriteStatus)
                )
            }
        }
    }
}