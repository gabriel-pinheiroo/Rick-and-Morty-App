package com.example.rickandmorty.features.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.use_cases.EpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val useCase: EpisodeUseCase
): ViewModel() {

    private val _state = MutableStateFlow(EpisodeState.Idle)
    val state = _state
        .onStart { getEpisodes() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = EpisodeState.Idle,
        )

    private fun getEpisodes() {
        viewModelScope.launch {
            _state.update{ it.onLoading() }
            try {
                val episodes = useCase.getEpisodes().getOrThrow()
                _state.update { it.onEpisodesLoaded(episodes) }
            } catch (e: Throwable) {
                println("Could not get episodes. ex: $e")
                _state.update { it.onLoadingFinished() }
            }finally {
                _state.update { it.onLoadingFinished() }
            }
        }
    }
}