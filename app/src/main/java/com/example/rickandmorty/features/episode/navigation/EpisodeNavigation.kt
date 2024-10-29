package com.example.rickandmorty.features.episode.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.rickandmorty.features.episode.EpisodeRoute
import com.example.rickandmorty.navigation.Routes

fun NavController.navigateToEpisode(topLevelNavOptions: NavOptions) {
    navigate(route = Routes.Episode)
}

fun NavGraphBuilder.episodeScreen() {
    composable<Routes.Episode> {
        EpisodeRoute()
    }
}