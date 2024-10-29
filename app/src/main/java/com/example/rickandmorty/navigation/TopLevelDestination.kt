package br.com.pedropinheiro.dox_e.navigation

import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.rickandmorty.features.episode.navigation.navigateToEpisode
import com.example.rickandmorty.features.location.navigation.navigateToLocation
import com.example.rickandmorty.features.character.navigation.navigateToCharacter
import com.example.rickandmorty.navigation.Routes

interface TopLevelDestination

fun <T : TopLevelDestination> NavController.navigateToTopLevelDestination(topLevelDestination: T) {
    currentDestination
        ?.let { route ->
            if (route.route == topLevelDestination::class.java.canonicalName.orEmpty()) return
        }

    val topLevelNavOptions = navOptions {
        popUpTo(Routes.Character) {
            saveState = true
            inclusive = false
        }
        launchSingleTop = true
        restoreState = true
    }

    when (topLevelDestination) {
        Routes.Character -> navigateToCharacter(topLevelNavOptions)
        Routes.Location -> navigateToLocation(topLevelNavOptions)
        Routes.Episode-> navigateToEpisode(topLevelNavOptions)
    }
}
