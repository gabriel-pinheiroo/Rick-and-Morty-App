package com.example.rickandmorty.features.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmorty.components.base.RickAndMortyLoading
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
    val lazyListState = rememberLazyListState()
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        bottomBarManager.showBottomBar()
        topBarManager.showTopBar()
        topBarManager.setTopBarConfig(
            TopBarConfig(
                title = "Locations",
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
            RickAndMortyLoading(
                size = 16.dp,
            )
        }
    } else {
        LazyColumn(
            state = lazyListState,
            modifier = modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(state.locations) { location ->
                LocationCard(location)
            }
        }
    }
}

@Composable
private fun LocationCard(location: Location) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = location.name,
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Location type:",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = location.type,
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Dimension:",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = location.dimension,
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}