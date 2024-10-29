package com.example.rickandmorty.features.character.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.rickandmorty.features.character.CharacterRoute
import com.example.rickandmorty.navigation.Routes

fun NavController.navigateToCharacter(topLevelNavOptions: NavOptions) {
    navigate(route = Routes.Character)
}

fun NavGraphBuilder.characterScreen() {
    composable<Routes.Character> {
        CharacterRoute()
    }
}