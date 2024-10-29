package com.example.rickandmorty.components.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class BottomBarManager {

    var shouldShowBottomBar by mutableStateOf(false)
        private set

    fun showBottomBar() {
        shouldShowBottomBar = true
    }

    fun hideBottomBar() {
        shouldShowBottomBar = false
    }
}