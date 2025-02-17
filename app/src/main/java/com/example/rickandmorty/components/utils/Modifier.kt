package com.example.rickandmorty.components.utils

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmorty.navigation.LocalNavAnimatedVisibilityScope
import com.example.rickandmorty.navigation.LocalSharedTransitionScope

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun Modifier.sharedElement(
    key: String,
): Modifier {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current

    if (sharedTransitionScope == null || animatedVisibilityScope == null) {
        return this
    }

    return with(sharedTransitionScope) {
        sharedElement(
            state = rememberSharedContentState(
                key = key,
            ),
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
}
