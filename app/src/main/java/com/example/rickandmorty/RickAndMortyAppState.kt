package com.example.rickandmorty

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.components.bottombar.BottomBarMenuItem
import com.example.rickandmorty.navigation.Routes

@Composable
fun rememberRickAndMortyAppState(
    menu: Set<BottomBarMenuItem> = setOf(),
    navController: NavController = rememberNavController()
) = remember(
    menu,
    navController
) {
    RickAndMortyAppState(
        menu = menu,
        navController = navController
    )
}

@Stable
class RickAndMortyAppState(
    val menu: Set<BottomBarMenuItem>,
    val navController: NavController,
) {
    var isBottomBarEnabled by mutableStateOf(false)

    private val fullScreenDestinations: Set<String> = setOf(
        Routes.Splash::class.java.canonicalName.orEmpty(),
    )

    init {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            isBottomBarEnabled = destination.route !in fullScreenDestinations
        }
    }

}

private fun String.parameterizedDestination() = this.plus("/{args}")