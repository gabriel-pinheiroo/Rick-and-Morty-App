package com.example.rickandmorty.features.character.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.features.character.CharacterRoute
import com.example.rickandmorty.navigation.Routes
import com.example.rickandmorty.navigation.animatedComposable

fun NavController.navigateToCharacter(topLevelNavOptions: NavOptions) {
    navigate(route = Routes.Character)
}

fun NavGraphBuilder.characterScreen(
    onCharacterClicked: (Character) -> Unit = {},
) {
    animatedComposable<Routes.Character> {
        CharacterRoute(
            onCharacterClicked = onCharacterClicked,
        )
    }
}