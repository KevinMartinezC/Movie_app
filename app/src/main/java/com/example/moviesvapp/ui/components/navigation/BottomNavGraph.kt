package com.example.moviesvapp.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesvapp.ui.components.favorite.FavoriteScreen
import com.example.moviesvapp.ui.components.home.MoviesScreen
import com.example.moviesvapp.ui.components.home.viewmodel.MoviesViewModel


@Composable
fun BottomNavGraph(
    navController: NavHostController) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(route = BottomNavItem.Home.route) {
            MoviesScreen(viewModel = MoviesViewModel(context))
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen()
        }
    }
}