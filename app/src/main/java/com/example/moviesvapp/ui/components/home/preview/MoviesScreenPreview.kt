package com.example.moviesvapp.ui.components.home.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesvapp.model.Movie
import com.example.moviesvapp.ui.components.home.MovieUiState
import com.example.moviesvapp.ui.components.home.MoviesScreen


@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    val sampleMovies = listOf(
        Movie(
            title = "Sample Movie 1",
            poster = "https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0Ym" +
                    "UtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg",
            type = "Action",
            year = "2022",
            imdbID = "tt3896198"
        ),
        Movie(
            title = "Sample Movie 2",
            poster = "https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0Ym" +
                    "UtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg",
            type = "Comedy",
            year = "2021",
            imdbID = "tt3896199"
        )
    )

    val sampleUiState = MovieUiState(movies = sampleMovies, isLoading = false, favoriteMovies = emptySet())

    MoviesScreen(
        uiState = sampleUiState,
        searchMovies = { _, _ -> },
        onToggleFavorite = { _ -> }
    )
}
