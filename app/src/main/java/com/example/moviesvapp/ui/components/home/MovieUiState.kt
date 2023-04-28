package com.example.moviesvapp.ui.components.home

import com.example.moviesvapp.model.Movie

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)
