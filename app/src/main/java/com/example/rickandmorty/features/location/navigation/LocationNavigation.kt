package com.example.rickandmorty.features.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.rickandmorty.features.location.LocationRoute
import com.example.rickandmorty.navigation.Routes

fun NavController.navigateToLocation(topLevelNavOptions: NavOptions) {
    navigate(route = Routes.Location)
}

fun NavGraphBuilder.locationScreen() {
    composable<Routes.Location> {
        LocationRoute()
    }
}