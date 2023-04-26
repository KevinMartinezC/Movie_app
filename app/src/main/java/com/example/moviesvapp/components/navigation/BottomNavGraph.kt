package com.example.moviesvapp.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesvapp.components.MoviesScreen
import com.example.moviesvapp.model.MoviesViewModel


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(route = BottomNavItem.Home.route) {
            MoviesScreen(viewModel = MoviesViewModel())

      }
        composable(route = BottomNavItem.Favorite.route) {

        }
    }
}