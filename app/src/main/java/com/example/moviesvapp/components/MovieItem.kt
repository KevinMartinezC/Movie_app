package com.example.moviesvapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesvapp.model.Movie

@Composable
fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(model = movie.poster)
        Image(
            painter = painter,
            contentDescription = "Movie Poster",
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
            Text(movie.title, style = MaterialTheme.typography.bodyLarge
            )
            Text(movie.year)
        }
    }
}