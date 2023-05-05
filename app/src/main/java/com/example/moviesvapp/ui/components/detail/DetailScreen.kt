package com.example.moviesvapp.ui.components.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesvapp.data.omdbapi.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movie: Movie, openBottomSheet: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = openBottomSheet,
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState
    ) {
        MovieDetails(
            poster = movie.poster,
            type = movie.type,
            year = movie.year
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
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
