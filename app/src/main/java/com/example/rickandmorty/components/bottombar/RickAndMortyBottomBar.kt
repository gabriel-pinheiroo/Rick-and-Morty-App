package com.example.rickandmorty.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RickAndMortyBottomBar(
    modifier: Modifier = Modifier,
    menu: Set<BottomBarMenuItem>,
    onItemClicked: (BottomBarMenuItem) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(bottom = 16.dp)
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        menu.forEach { item ->
            BottomBarButton(
                item = item,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Composable
fun BottomBarButton(
    item: BottomBarMenuItem,
    onItemClicked: (BottomBarMenuItem) -> Unit
) {
    Button(
        onClick = { onItemClicked(item) },
        content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = Color.Blue
                    )
                    Text(
                        text = item.name,
                        color = Color.Blue,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            },
        shape = ButtonDefaults.textShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (item.isSelected) Color.Blue else Color.Gray
        ),
    )
}

@Preview
@Composable
private fun BottomBarPrev() {
    RickAndMortyBottomBar(
        menu = setOf(
            BottomBarMenuItem("home", "Home", Icons.Rounded.LocationOn),
            BottomBarMenuItem("groups", "Groups", Icons.Rounded.Home),
            BottomBarMenuItem("profile", "Profile", Icons.Rounded.Add),
        ),
        onItemClicked = {}
    )
}
