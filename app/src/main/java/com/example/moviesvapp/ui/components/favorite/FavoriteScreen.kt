package com.example.moviesvapp.ui.components.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesvapp.R
import com.example.moviesvapp.model.database.FavoriteMovie
import com.example.moviesvapp.ui.theme.MyApplicationTheme
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FavoriteScreen(favoriteMoviesFlow: StateFlow<List<FavoriteMovie>>) {
    val favoriteMovies by favoriteMoviesFlow.collectAsState(initial = emptyList())

    MyApplicationTheme {
        LazyColumn {
            items(favoriteMovies.size) { index ->
                val favoriteMovie = favoriteMovies[index]
                FavoriteMovieItem(favoriteMovie)
                Divider()
            }
        }
    }
}

@Composable
fun FavoriteMovieItem(favoriteMovie: FavoriteMovie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(model = favoriteMovie.poster)
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier
                .height(100.dp)
                .width(75.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                favoriteMovie.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                favoriteMovie.year,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
