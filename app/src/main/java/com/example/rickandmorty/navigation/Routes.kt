package com.example.rickandmorty.navigation

import br.com.pedropinheiro.dox_e.navigation.TopLevelDestination
import com.example.rickandmorty.features.characterDetails.navigation.CharacterDetailsArgs
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

    @Serializable
    data class CharacterDetails(val args: CharacterDetailsArgs): Routes
}