package com.example.moviesvapp.ui.components.detail.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesvapp.model.Movie
import com.example.moviesvapp.ui.components.detail.DetailScreen

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val sampleMovie = Movie(
        imdbID = "tt3896198",
        title = "Guardians of the Galaxy ",
        year = "05 May 2017",
        poster = "https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0Ym" +
                "UtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg",
        type = "Movie"
    )
    val openBottomSheet: () -> Unit = {}
    DetailScreen(sampleMovie, openBottomSheet)
}