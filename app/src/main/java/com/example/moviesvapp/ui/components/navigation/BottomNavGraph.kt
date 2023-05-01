package com.example.moviesvapp.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesvapp.ui.components.favorite.FavoriteScreen
import com.example.moviesvapp.ui.components.home.MoviesScreen
import com.example.moviesvapp.ui.components.home.viewmodel.MoviesViewModel


@Composable
fun BottomNavGraph(
    navController: NavHostController
) {

    val context = LocalContext.current
    val moviesViewModel = remember { MoviesViewModel(context) }
    val uiState by moviesViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(route = BottomNavItem.Home.route) {
            MoviesScreen(
                uiState = uiState,
                searchMovies = moviesViewModel::searchMovies,
                onToggleFavorite = { movie -> moviesViewModel.toggleFavorite(movie) }
            )
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen(favoriteMoviesFlow = moviesViewModel.favoriteMoviesFlow)
        }
    }
}
