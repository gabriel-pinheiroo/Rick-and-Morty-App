package com.example.rickandmorty.components.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.rickandmorty.components.topbar.TopBarConfig

class TopBarManager {

    var shouldShowTopBar by mutableStateOf(false)
        private set

    var config by mutableStateOf(TopBarConfig())
        private set

    fun setTopBarConfig(config: TopBarConfig) {
        this.config = config
    }

    fun showTopBar() {
        shouldShowTopBar = true
    }

    fun hideTopBar() {
        shouldShowTopBar = false
    }
}