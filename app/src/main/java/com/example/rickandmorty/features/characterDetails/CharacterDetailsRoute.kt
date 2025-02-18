package com.example.rickandmorty.features.characterDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.palette.graphics.Palette
import coil.Coil
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.rickandmorty.R
import com.example.rickandmorty.components.base.RickAndMortyLoading
import com.example.rickandmorty.components.topbar.TopBarConfig
import com.example.rickandmorty.features.theme.LocalTopBarManager
import kotlinx.coroutines.delay

@Composable
fun CharacterDetailsRoute(
    modifier: Modifier = Modifier,
    characterId: Int = 0,
    characterName: String = "",
    onBackPressed: () -> Unit = {},
    viewModel: CharacterDetailsViewModel = hiltViewModel<CharacterDetailsViewModel, CharacterDetailsViewModel.Factory> { factory ->
        factory.create(characterId)
    }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CharacterDetailsScreen(
        modifier = modifier,
        state = state,
        characterName = characterName,
        onBackPressed = onBackPressed
    )
}

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    characterName: String = "",
    state: CharacterDetailsState = CharacterDetailsState.IDLE,
    onBackPressed: () -> Unit = {},
) {
    val topBarManager = LocalTopBarManager.current
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        topBarManager.showTopBar()
        topBarManager.setTopBarConfig(
            config = TopBarConfig(
                showTitle = true,
                title = characterName,
                showBackButton = true,
                onBackClicked = onBackPressed
            )
        )
        delay(1000)
        isLoading = false
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            RickAndMortyLoading(
                size = 16.dp,
            )
        }
    } else {
        CharacterDetailsContent(
            state = state,
            modifier = modifier
        )
    }
}

@Composable
fun CharacterDetailsContent(
    modifier: Modifier = Modifier,
    state: CharacterDetailsState = CharacterDetailsState.IDLE,
) {

    val context = LocalContext.current
    var backgroundColor by remember { mutableStateOf(Color.DarkGray) }

    LaunchedEffect(state.character.image) {
        val result = Coil.imageLoader(context)
            .execute(
                ImageRequest.Builder(context)
                    .data(state.character.image)
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
            .data(state.character.image)
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
                .size(170.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier,
                painter = painter,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        color = if (state.character.status == stringResource(R.string.alive)) {
                            Color.Green
                        } else if (state.character.status == stringResource(R.string.dead)) {
                            Color.Red
                        } else {
                            Color.Black
                        },
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = state.character.status,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.species),
                color = Color.Gray,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = state.character.species,
                color = Color.White,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.gender),
                color = Color.Gray,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = state.character.gender,
                color = Color.White,
                fontSize = 16.sp,
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = stringResource(R.string.last_known_location),
                color = Color.Gray,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = state.character.location.name,
                color = Color.White,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.first_seen_in),
                color = Color.Gray,
                fontSize = 16.sp,
            )

            Text(
                text = state.character.origin.name,
                color = Color.White,
                fontSize = 16.sp,
            )
        }


    }
}
