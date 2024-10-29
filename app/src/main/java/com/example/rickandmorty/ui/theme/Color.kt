package com.example.rickandmorty.ui.theme

import android.graphics.Color.parseColor
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

fun String.toColor() = Color(color = parseColor(this))


@Stable
val Color.Companion.Alabaster
    get() = "#F9F9F9".toColor()

val Color.Companion.Mercury
    get() = "#E5E5E5".toColor()