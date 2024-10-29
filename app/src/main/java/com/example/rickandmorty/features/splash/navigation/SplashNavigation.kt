package com.example.rickandmorty.features.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rickandmorty.features.splash.SplashRoute
import com.example.rickandmorty.navigation.Routes

fun NavGraphBuilder.splashScreen(
    onDone: () -> Unit,
) {
    composable<Routes.Splash> {
        SplashRoute(
            onDone = onDone
        )
    }
}

