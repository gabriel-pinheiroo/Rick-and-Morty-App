package com.example.rickandmorty.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TopBarConfig(
    val title: String = "",
    val showTitle: Boolean = false
)

@Composable
fun RickAndMortyTopBar(
    modifier: Modifier = Modifier,
    config: TopBarConfig = TopBarConfig(),
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            if (config.showTitle) {
                Text(
                    text = config.title,
                    color = Color.LightGray,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}