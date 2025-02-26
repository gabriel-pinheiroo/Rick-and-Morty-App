package com.example.rickandmorty.features.location

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmorty.R
import com.example.rickandmorty.components.base.RickAndMortyOrbitLoading
import com.example.rickandmorty.components.topbar.TopBarConfig
import com.example.rickandmorty.domain.models.Location
import com.example.rickandmorty.features.theme.LocalBottomBarManager
import com.example.rickandmorty.features.theme.LocalTopBarManager
import kotlinx.coroutines.delay

@Composable
fun LocationRoute(
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    BackHandler {}

    LocationScreen(
        modifier = modifier,
        state = state,
        onLoadMore = viewModel::loadMoreLocations
    )
}

@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    state: LocationState = LocationState.Idle,
    onLoadMore: () -> Unit = {},
) {

    val bottomBarManager = LocalBottomBarManager.current
    val topBarManager = LocalTopBarManager.current
    val lazyListState = rememberLazyStaggeredGridState()
    var isLoading by remember { mutableStateOf(true) }
    val title = stringResource(id = R.string.locations)

    LaunchedEffect(Unit) {
        bottomBarManager.showBottomBar()
        topBarManager.showTopBar()
        topBarManager.setTopBarConfig(
            TopBarConfig(
                title = title,
                showTitle = true
            )
        )
        delay(1000)
        isLoading = false
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
            onLoadMore()
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            RickAndMortyOrbitLoading()
        }
    } else {
        LazyVerticalStaggeredGrid(
            state = lazyListState,
            columns = StaggeredGridCells.Adaptive(150.dp),
            modifier = modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(
                items = state.locations,
                key = { location -> location.id }
            ) { location ->
                LocationCardListItem(location)
            }
        }
    }
}

@Composable
fun LocationCardListItem(
    location: Location
) {
    ListItem(
        headlineContent = { Text(location.name, fontWeight = Bold) },
        supportingContent = { Text(location.dimension) },
        overlineContent = { Text(location.type) },
        colors = ListItemDefaults.colors(
            containerColor = Color.Gray,
            headlineColor = Color.White,
            supportingColor = Color.White,
            overlineColor = Color.White
        ),
        modifier = Modifier.clip(RoundedCornerShape(8.dp))
    )
}
