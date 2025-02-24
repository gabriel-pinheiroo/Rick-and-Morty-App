package com.example.rickandmorty.features.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.database.CharacterDao
import com.example.rickandmorty.domain.use_cases.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
    private val characterDao: CharacterDao
) : ViewModel() {

    private enum class FETCH_CARACTERS_REASON {
        INITIAL,
        PAGINATING
    }

    private val _state = MutableStateFlow(CharacterState.Idle)
    val state = _state
        .onStart { getCharacters() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = CharacterState.Idle,
        )

    private var currentPage = 1
    private var isLastPage = false

    private fun getCharacters(reason: FETCH_CARACTERS_REASON = FETCH_CARACTERS_REASON.INITIAL) {
        if (isLastPage) return
        viewModelScope.launch {
            _state.update {
                when (reason) {
                    FETCH_CARACTERS_REASON.INITIAL -> it.onLoading()
                    FETCH_CARACTERS_REASON.PAGINATING -> it.onPaginating()
                }
            }
            try {
                val characters = characterUseCase.getCharacters(page = currentPage).getOrThrow()
                if (characters.isEmpty()) {
                    isLastPage = true
                } else {
                    _state.update { currentState ->
                        currentState.onCharactersLoaded(
                            (currentState.characters + characters).distinctBy { it.id }
                        )
                    }
                    currentPage++
                }
            } catch (e: Throwable) {
                println("Could not get characters. ex: $e")
                _state.update { it.onLoadingFinished() }
            }finally {
                delay(1000)
                _state.update { it.onLoadingFinished() }
            }
        }
    }

    fun loadMoreCharacters() {
        getCharacters(reason = FETCH_CARACTERS_REASON.PAGINATING)
    }
}