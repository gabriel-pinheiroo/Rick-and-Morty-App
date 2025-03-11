package com.example.rickandmorty.features.favoriteCharacters

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.palette.graphics.Palette
import coil.Coil
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.rickandmorty.R
import com.example.rickandmorty.components.topbar.TopBarConfig
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.features.theme.LocalTopBarManager

@Composable
fun FavoriteCharactersRoute(
    modifier: Modifier = Modifier,
    viewModel: FavoriteCharactersViewModel = hiltViewModel(),
    onBackClicked: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoriteCharactersScreen(
        modifier = modifier,
        state = state,
        onBackClicked = onBackClicked,
    )
}

@Composable
fun FavoriteCharactersScreen(
    modifier: Modifier = Modifier,
    state: FavoriteCharactersState = FavoriteCharactersState.IDLE,
    onBackClicked: () -> Unit = {},
) {
    val topBarManager = LocalTopBarManager.current
    val title = stringResource(R.string.favorite_characters)

    LaunchedEffect(Unit) {
        topBarManager.showTopBar()
        topBarManager.setTopBarConfig(
            TopBarConfig(
                title = title,
                showTitle = true,
                showBackButton = true,
                onBackClicked = onBackClicked,
            )
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(state.favoriteCharacters) { character ->
            FavoriteCharacterCard(
                character = character,
            )
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun FavoriteCharacterCard(
    character: Character,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var backgroundColor by remember { mutableStateOf(Color.DarkGray) }

    LaunchedEffect(character.image) {
        val result = Coil.imageLoader(context)
            .execute(
                ImageRequest.Builder(context)
                    .data(character.image)
                    .allowHardware(false)
                    .build()
            )

        val bitmap = (result.drawable as? android.graphics.drawable.BitmapDrawable)?.bitmap
        bitmap?.let {
            val palette = Palette.from(it).generate()
            palette.dominantSwatch?.rgb?.let { colorValue ->
                backgroundColor = Color(colorValue)
            }
        }
    }

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context)
            .data(character.image)
            .crossfade(true)
            .build()
    )

        Column(
            modifier = modifier
                .background(Color.DarkGray)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = character.name,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            color = if (character.status == stringResource(R.string.alive)) {
                                Color.Green
                            } else if (character.status == stringResource(R.string.dead)) {
                                Color.Red
                            } else {
                                Color.Black
                            },
                            shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = character.status,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
}

@Preview
@Composable
private fun FavoriteCharacterCardPrev() {
    FavoriteCharacterCard(
        character = Character(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human",
        )
    )
}