package com.example.moviesvapp.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home : BottomNavItem(
        route = "home",
        title = "Home",
        icon = Icons.Default.Search
    )
    object Favorite : BottomNavItem(
        route = "favorite",
        title = "Favorite",
        icon = Icons.Default.Star
    )
}
