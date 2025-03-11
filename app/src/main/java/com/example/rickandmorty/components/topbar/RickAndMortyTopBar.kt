package com.example.rickandmorty.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TopBarConfig(
    var title: String = "",
    var showTitle: Boolean = false,
    var showBackButton: Boolean = false,
    var onBackClicked: () -> Unit = {},
    var showFavoriteButton: Boolean = false,
    var onFavoriteClicked: () -> Unit = {},
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
            if (config.showBackButton) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.LightGray,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 24.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            config.onBackClicked()
                        }
                )
            }

            if (config.showTitle) {
                Text(
                    text = config.title,
                    color = Color.LightGray,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            if (config.showFavoriteButton) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.LightGray,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 24.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            config.onFavoriteClicked()
                        }
                )
            }
        }
    }
}