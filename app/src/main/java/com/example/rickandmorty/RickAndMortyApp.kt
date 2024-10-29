package com.example.rickandmorty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.pedropinheiro.dox_e.navigation.navigateToTopLevelDestination
import com.example.rickandmorty.components.bottombar.BottomBarMenuItem
import com.example.rickandmorty.components.bottombar.RickAndMortyBottomBar
import com.example.rickandmorty.components.topbar.RickAndMortyTopBar
import com.example.rickandmorty.features.theme.LocalTopBarManager
import com.example.rickandmorty.navigation.RickAndMortyNavHost
import com.example.rickandmorty.navigation.Routes

@Composable
fun RickAndMortyApp(
    state: RickAndMortyAppState,
    navController: NavHostController,
    onMenuSelected: (BottomBarMenuItem) -> Unit,
) {
    val topBarManager = LocalTopBarManager.current

    Scaffold(
        bottomBar = {
            if (state.isBottomBarEnabled) {
                RickAndMortyBottomBar(
                    menu = state.menu,
                    onItemClicked = {
                        onMenuSelected(it)
                        when (it.route) {
                            "character" -> navController.navigateToTopLevelDestination(Routes.Character)
                            "episode" -> navController.navigateToTopLevelDestination(Routes.Episode)
                            "location" -> navController.navigateToTopLevelDestination(Routes.Location)
                        }
                    },
                )
            }
        },
        topBar = {
            Column {
                if (topBarManager.shouldShowTopBar) {
                    RickAndMortyTopBar(config = topBarManager.config)
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentWindowInsets = WindowInsets(top = 48.dp),
    ) {
        RickAndMortyNavHost(
            navController = navController,
            paddingValues = it,
            onMenuSelected = onMenuSelected,
        )
    }
}