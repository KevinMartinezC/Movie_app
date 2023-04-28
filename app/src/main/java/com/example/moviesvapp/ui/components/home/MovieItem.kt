package com.example.moviesvapp.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesvapp.R
import com.example.moviesvapp.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(movie: Movie) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = {
                openBottomSheet = !openBottomSheet}),

        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(model = movie.poster)
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
                movie.title, style = MaterialTheme.typography.bodyLarge
            )

        }
    }
    if (openBottomSheet){
        val sheetState = rememberModalBottomSheetState()
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            content = {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(model = movie.poster),
                        contentDescription = stringResource(R.string.movie_poster),
                        modifier = Modifier.height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        movie.type,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(movie.year, modifier = Modifier.padding(top = 8.dp))
                }
            }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movie: Movie) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = {},
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(model = movie.poster),
                    contentDescription = stringResource(R.string.movie_poster),
                    modifier = Modifier.height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    movie.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(movie.year, modifier = Modifier.padding(top = 8.dp))
            }
        }
    )
}
