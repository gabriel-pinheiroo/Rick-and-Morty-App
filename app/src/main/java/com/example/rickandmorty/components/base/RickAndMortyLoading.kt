package com.example.rickandmorty.components.base

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RickAndMortyLoading(
    modifier: Modifier = Modifier,
    numberOfDots: Int = 3,
    size: Dp = 8.dp,
    delayUnit: Int = 200,
    spaceBetween: Dp = 2.dp,
    color: Color = Color.White,
) {
    val maxOffset = (numberOfDots * 2).toFloat()
    val duration = remember(numberOfDots, delayUnit) {
        numberOfDots * delayUnit
    }

    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")

    val offsets: List<State<Float>> = List(numberOfDots) { index ->
        animateOffsetWithDelay(
            infiniteTransition = infiniteTransition,
            duration = duration,
            delay = index * delayUnit,
            delayUnit = delayUnit,
            maxOffset = maxOffset
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween),
        modifier = modifier.padding(top = maxOffset.dp)
    ) {
        offsets.forEach { offsetState ->
            Dot(offset = offsetState.value, size = size, color = color)
        }
    }
}

@Composable
private fun Dot(
    offset: Float,
    size: Dp,
    color: Color
) {
    Spacer(
        Modifier
            .size(size)
            .offset(y = -offset.dp)
            .background(
                color = color,
                shape = CircleShape
            )
    )
}

@Composable
private fun animateOffsetWithDelay(
    infiniteTransition: InfiniteTransition,
    duration: Int,
    delay: Int,
    delayUnit: Int,
    maxOffset: Float
): State<Float> {
    return infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration
                0f at delay using LinearEasing
                maxOffset at delay + delayUnit using LinearEasing
                0f at delay + (duration / 2)
            },
        ),
        label = "animation",
    )
}