package com.example.rickandmorty

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.components.bottombar.BottomBarMenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _menu = MutableStateFlow(setOf<BottomBarMenuItem>())
    val menu = _menu.asStateFlow()

    init {
        setMenuDefaults()
    }

    private fun setMenuDefaults() {
        buildSet {
            add(BottomBarMenuItem(name = "Personagens", route = "character", icon = Icons.Rounded.Person, isSelected = true))
            add(BottomBarMenuItem(name = "Episódios", route = "episode", icon = Icons.Rounded.Menu))
            add(BottomBarMenuItem(name = "Locais", route = "location", icon = Icons.Rounded.LocationOn))
        }.let {
            _menu.update { it }
        }
    }

    fun onMenuSelected(item: BottomBarMenuItem) {
        _menu.update { currentMenu ->
            currentMenu.map { men ->
                men.copy(isSelected = men.route == item.route)
            }.toSet()
        }
    }
}
