package com.example.moviesvapp.ui.components.favorite.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesvapp.model.database.FavoriteMovie
import com.example.moviesvapp.ui.components.favorite.FavoriteScreen
import kotlinx.coroutines.flow.MutableStateFlow


@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    val sampleFavoriteMovies = listOf(
        FavoriteMovie(
            title = "Sample Movie 1",
            poster = "https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0Ym" +
                    "UtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg",
            type = "Action",
            year = "2022",
            imdbID = "tt3896198",

            ),
        FavoriteMovie(
            title = "Sample Movie 2",
            poster = "https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0Ym" +
                    "UtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg",
            type = "Comedy",
            year = "2021",
            imdbID = "tt3896198",

            ),
        FavoriteMovie(
            title = "Sample Movie 3",
            poster = "https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0Ym" +
                    "UtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg",
            type = "Sci-Fi",
            year = "2020",
            imdbID = "tt3896198",

            )
    )
    val sampleFavoriteMoviesFlow = MutableStateFlow(sampleFavoriteMovies)

    FavoriteScreen(favoriteMoviesFlow = sampleFavoriteMoviesFlow)
}
