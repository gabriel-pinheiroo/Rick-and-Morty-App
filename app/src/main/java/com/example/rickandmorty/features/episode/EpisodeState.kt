package com.example.rickandmorty.features.episode

import com.example.rickandmorty.domain.models.Episode

data class EpisodeState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val episodes: List<Episode> = emptyList(),
) {
    fun onLoading() = copy(isLoading = true, hasError = false)
    fun onLoadingFinished() = copy(isLoading = false)
    fun onEpisodesLoaded(episodes: List<Episode>) = copy(episodes = episodes)

    companion object {
        internal val Idle = EpisodeState()
    }
}
