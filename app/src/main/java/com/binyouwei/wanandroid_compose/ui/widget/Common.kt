package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*

@Composable
fun BottomNavigation() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")
    BottomNavigation {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}