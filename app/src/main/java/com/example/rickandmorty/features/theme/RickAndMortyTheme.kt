package com.example.rickandmorty.features.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.rickandmorty.components.utils.BottomBarManager
import com.example.rickandmorty.components.utils.TopBarManager
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.DarkGray,
    )

    CompositionLocalProvider(
        LocalTopBarManager provides topBarManager,
        LocalBottomBarManager provides bottomBarManager,
    ) {
        content()
    }
}