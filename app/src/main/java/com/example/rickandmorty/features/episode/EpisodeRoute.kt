package com.example.rickandmorty.features.episode

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EpisodeRoute(
    modifier: Modifier = Modifier,
    viewModel: EpisodeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    EpisodeScreen(
        modifier = modifier,
        state = state
    )
}


@Composable
fun EpisodeScreen(
    modifier: Modifier = Modifier,
    state: EpisodeState = EpisodeState.Idle
) {
    LazyColumn {
        items(state.episodes) { episode ->
            Text(
                text = episode.name
            )
        }
    }

}