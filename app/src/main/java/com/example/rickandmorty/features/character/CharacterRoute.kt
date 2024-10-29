package com.example.rickandmorty.features.character

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmorty.components.topbar.TopBarConfig
import com.example.rickandmorty.features.theme.LocalBottomBarManager
import com.example.rickandmorty.features.theme.LocalTopBarManager

@Composable
fun CharacterRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    CharacterScreen(
        modifier = modifier,
        state = state
    )
}

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    state: CharacterState = CharacterState.Idle,
) {
    val bottomBarManager = LocalBottomBarManager.current
    val topBarManager = LocalTopBarManager.current

    LaunchedEffect(Unit) {
        bottomBarManager.showBottomBar()
        topBarManager.showTopBar()
        topBarManager.setTopBarConfig(
            TopBarConfig(
                title = "Personagens",
                showTitle = true
            )
        )
    }

    LazyColumn {
        items(state.characters) { character ->
            Text(
                text = character.name
            )
        }
    }

}