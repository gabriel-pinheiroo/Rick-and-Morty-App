package com.example.rickandmorty.components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarMenuItem (
    val name: String = "",
    val route: String = "",
    val icon: ImageVector = Icons.Rounded.Person,
    val isSelected: Boolean = false
)