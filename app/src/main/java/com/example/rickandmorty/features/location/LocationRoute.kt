package com.example.rickandmorty.features.location

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.Lazy

@Composable
fun LocationRoute(
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LocationScreen(
        modifier = modifier,
        state = state
    )
}

@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    state: LocationState = LocationState.Idle
) {
    LazyColumn {
        items(state.locations) { location ->
            Text(
                text = location.name
            )
        }
    }
}