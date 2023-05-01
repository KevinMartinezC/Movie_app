package com.example.moviesvapp.ui.components.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviesvapp.data.omdbapi.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movie: Movie, openBottomSheet: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(onDismissRequest = openBottomSheet,
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        content = {
            MovieDetails(
                poster = movie.poster,
                type = movie.type,
                year = movie.year
            )
        }
    )
}

