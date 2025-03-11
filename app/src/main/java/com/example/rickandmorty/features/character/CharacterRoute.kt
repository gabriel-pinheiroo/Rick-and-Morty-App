package com.example.rickandmorty.features.character

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.R
import com.example.rickandmorty.components.base.RickAndMortyOrbitLoading
import com.example.rickandmorty.components.topbar.TopBarConfig
import com.example.rickandmorty.components.utils.sharedElement
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.features.theme.LocalBottomBarManager
import com.example.rickandmorty.features.theme.LocalTopBarManager
import kotlinx.coroutines.delay

@Composable
fun CharacterRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel(),
    onCharacterClicked: (Character) -> Unit = {},
    onFavoriteClicked: () -> Unit = {},
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    BackHandler {}

    CharacterScreen(
        modifier = modifier,
        state = state,
        onLoadMore = {
            viewModel.loadMoreCharacters()
        },
        onCharacterClicked = onCharacterClicked,
        onFavoriteClicked = onFavoriteClicked
    )
}

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    state: CharacterState = CharacterState.Idle,
    onLoadMore: () -> Unit = {},
    onCharacterClicked: (Character) -> Unit = {},
    onFavoriteClicked: () -> Unit = {},
) {
    val bottomBarManager = LocalBottomBarManager.current
    val topBarManager = LocalTopBarManager.current
    val lazyListState = rememberLazyListState()
    val title = stringResource(R.string.characters)

    LaunchedEffect(Unit) {
        bottomBarManager.showBottomBar()
        topBarManager.showTopBar()
        topBarManager.setTopBarConfig(
            TopBarConfig(
                title = title,
                showTitle = true,
                showFavoriteButton = true,
                onFavoriteClicked = onFavoriteClicked
            )
        )
    }

    val shouldPaginate by remember {
        derivedStateOf {
            val lastVisibleIndex =
                lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            val totalItems = lazyListState.layoutInfo.totalItemsCount
            lastVisibleIndex >= totalItems - 1 && totalItems > 0
        }
    }

    LaunchedEffect(shouldPaginate) {
        if (shouldPaginate) {
            delay(1000)
            onLoadMore()
        }
    }
    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(
            items = state.characters,
            key = { character -> character.id }
        ) { character ->
            CharactersCard(
                character = character,
                onCharacterClicked = onCharacterClicked
            )
        }

        if (state.isPaginating) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    RickAndMortyOrbitLoading(
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CharactersCard(
    character: Character,
    modifier: Modifier = Modifier,
    onCharacterClicked: (Character) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray)
            .clickable(onClick = { onCharacterClicked(character) })
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .sharedElement(
                        key = character.id.toString(),
                    )
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = character.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(2.dp))

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

                    Text(
                        modifier = Modifier
                            .padding(start = 6.dp),
                        text = "${character.status} - ${character.species}",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}
