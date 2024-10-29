package com.example.rickandmorty.features.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.rickandmorty.components.utils.BottomBarManager
import com.example.rickandmorty.components.utils.TopBarManager

val LocalTopBarManager = staticCompositionLocalOf<TopBarManager> {
    error("No TopBarManager provided")
}
val LocalBottomBarManager = staticCompositionLocalOf<BottomBarManager> {
    error("No BottomBarManager provided")
}

@Composable
fun RickAndMortyTheme(
    content: @Composable () -> Unit,
) {
    val topBarManager = TopBarManager()
    val bottomBarManager = BottomBarManager()

    CompositionLocalProvider(
        LocalTopBarManager provides topBarManager,
        LocalBottomBarManager provides bottomBarManager,
    ) {
        content()
    }
}