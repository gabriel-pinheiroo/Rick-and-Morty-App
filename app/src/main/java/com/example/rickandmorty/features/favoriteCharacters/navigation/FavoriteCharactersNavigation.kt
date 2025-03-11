package com.example.rickandmorty.features.favoriteCharacters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rickandmorty.features.favoriteCharacters.FavoriteCharactersRoute
import com.example.rickandmorty.navigation.Routes

fun NavController.navigateToFavoriteCharacters() {
    navigate(route = Routes.FavoriteCharacters)
}

fun NavGraphBuilder.favoriteCharactersScreen(
    onBackClicked: () -> Unit
) {
    composable<Routes.FavoriteCharacters> {
        FavoriteCharactersRoute(
            onBackClicked = onBackClicked
        )
    }
}