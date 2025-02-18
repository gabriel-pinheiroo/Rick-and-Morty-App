package com.example.rickandmorty.features.characterDetails.navigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.toRoute
import androidx.navigation.compose.composable
import com.example.rickandmorty.features.characterDetails.CharacterDetailsRoute
import com.example.rickandmorty.navigation.LocalNavAnimatedVisibilityScope
import com.example.rickandmorty.navigation.Routes
import kotlin.reflect.typeOf

fun NavController.navigateToCharacterDetails(
    characterId: Int,
    characterName: String,
    navOptions: NavOptions? = null,
) = navigate(
    route = Routes.CharacterDetails(
        args = CharacterDetailsArgs(
            characterId = characterId,
            characterName = characterName
        )
    ), navOptions
)

fun NavGraphBuilder.characterDetailsScreen(
    onBackPressed: () -> Unit = {},
) {
    composable<Routes.CharacterDetails>(
        typeMap = mapOf(typeOf<CharacterDetailsArgs>() to CharacterDetailsArgsType),
    ) { backStackEntry ->

        val characterDetails: Routes.CharacterDetails = backStackEntry.toRoute()
        val (id, name) = characterDetails.args
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this,
        ) {
            CharacterDetailsRoute(
                characterId = id,
                characterName = name,
                onBackPressed = onBackPressed,
            )
        }
    }
}
