package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.features.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val menu by viewModel.menu.collectAsStateWithLifecycle()
            val appState = rememberRickAndMortyAppState(
                menu = menu,
                navController = navController
            )

            RickAndMortyTheme {
                RickAndMortyApp(
                    state = appState,
                    navController = navController,
                    onMenuSelected = {
                        viewModel.onMenuSelected(it)
                    },
                )
            }
        }
    }
}


