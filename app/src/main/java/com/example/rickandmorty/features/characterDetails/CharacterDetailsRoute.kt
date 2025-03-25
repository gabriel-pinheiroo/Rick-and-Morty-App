package com.example.rickandmorty.features.characterDetails

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.components.topbar.TopBarConfig
import com.example.rickandmorty.components.utils.sharedElement
import com.example.rickandmorty.features.theme.LocalTopBarManager

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
        onBackPressed = onBackPressed,
        updateFavorite = viewModel::updateFavorite,
    )
}

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    characterName: String = "",
    state: CharacterDetailsState = CharacterDetailsState.IDLE,
    onBackPressed: () -> Unit = {},
    updateFavorite: () -> Unit = {},
) {
    val topBarManager = LocalTopBarManager.current

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
    }
    CharacterDetailsContent(
        state = state,
        modifier = modifier,
        updateFavorite = updateFavorite,
    )
}

@Composable
fun CharacterDetailsContent(
    modifier: Modifier = Modifier,
    state: CharacterDetailsState = CharacterDetailsState.IDLE,
    updateFavorite: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(Color.DarkGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterImageSection(state)
        Spacer(modifier = Modifier.height(16.dp))
        CharacterInfoSection(state, updateFavorite)
    }
}

@Composable
fun CharacterImageSection(state: CharacterDetailsState) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .sharedElement(state.character.id.toString()),
        model = state.character.image,
        contentDescription = null,
    )
}

@Composable
fun CharacterInfoSection(state: CharacterDetailsState, updateFavorite: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 24.dp)
    ) {
        FavoriteButton(isFavorite = state.character.isFavorite, updateFavorite = updateFavorite)

        InfoRow(label = stringResource(R.string.species), value = state.character.species)
        InfoRow(label = stringResource(R.string.gender), value = state.character.gender)
        InfoRow(label = stringResource(R.string.last_known_location), value = state.character.location.name)
        InfoRow(label = stringResource(R.string.first_seen_in), value = state.character.origin.name)

        CharacterStatusIndicator(state.character.status)
    }
}

@Composable
fun FavoriteButton(isFavorite: Boolean, updateFavorite: () -> Unit) {
    IconToggleButton(
        modifier = Modifier
            .padding(15.dp)
            .background(Color.White.copy(alpha = 0.9f), shape = CircleShape),
        checked = isFavorite,
        onCheckedChange = { updateFavorite() }
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            tint = Color.Black
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Text(text = label, color = Color.Gray, fontSize = 16.sp)
    Spacer(modifier = Modifier.height(4.dp))
    Text(text = value, color = Color.White, fontSize = 16.sp)
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun CharacterStatusIndicator(status: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(
            modifier = Modifier
                .size(6.dp)
                .background(
                    color = when (status) {
                        stringResource(R.string.alive) -> Color.Green
                        stringResource(R.string.dead) -> Color.Red
                        else -> Color.Black
                    },
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = status,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}
