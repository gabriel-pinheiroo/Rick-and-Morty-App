package com.example.rickandmorty.navigation

import br.com.pedropinheiro.dox_e.navigation.TopLevelDestination
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object Splash: Routes

    @Serializable
    data object Character: Routes, TopLevelDestination

    @Serializable
    data object Episode: Routes, TopLevelDestination

    @Serializable
    data object Location: Routes, TopLevelDestination
}