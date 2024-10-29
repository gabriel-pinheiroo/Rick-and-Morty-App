package com.example.rickandmorty.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.components.base.RickAndMortyLoading
import com.example.rickandmorty.ui.theme.Alabaster
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    modifier: Modifier = Modifier,
    onDone: () -> Unit = {},
) {
    SplashScreen(
        modifier = modifier,
        onDone = onDone,
        )
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onDone: () -> Unit = {},
) {

    LaunchedEffect(Unit) {
        delay(300)
        onDone()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Alabaster),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.fillMaxSize(0.6f),
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
            )

            RickAndMortyLoading(
                spaceBetween = 4.dp,
                size = 16.dp,
                color = Color.Blue,
            )
        }
    }
}
