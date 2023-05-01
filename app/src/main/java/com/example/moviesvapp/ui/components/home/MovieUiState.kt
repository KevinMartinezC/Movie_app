package com.example.moviesvapp.ui.components.home

import com.example.moviesvapp.data.omdbapi.model.Movie

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val favoriteMovies: Set<String> = emptySet()
)

