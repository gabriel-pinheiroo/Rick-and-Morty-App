package com.example.rickandmorty.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.components.bottombar.BottomBarMenuItem
import com.example.rickandmorty.features.character.navigation.characterScreen
import com.example.rickandmorty.features.characterDetails.navigation.characterDetailsScreen
import com.example.rickandmorty.features.characterDetails.navigation.navigateToCharacterDetails
import com.example.rickandmorty.features.episode.navigation.episodeScreen
import com.example.rickandmorty.features.location.navigation.locationScreen
import com.example.rickandmorty.ui.theme.Alabaster


@Composable
fun RickAndMortyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues = PaddingValues(),
    startDestination: Routes = Routes.Character,
    onMenuSelected: (BottomBarMenuItem) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
            .background(Color.Alabaster)
            .padding(paddingValues),
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        characterScreen(
            onCharacterClicked = { character ->
                navController.navigateToCharacterDetails(
                    characterId = character.id,
                    characterName = character.name
                )
            }
        )
        episodeScreen()
        locationScreen()
        characterDetailsScreen(
            onBackPressed = navController::popBackStack
        )
    }

}