package com.example.moviesvapp.components.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviesvapp.components.AddItem

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorite,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}