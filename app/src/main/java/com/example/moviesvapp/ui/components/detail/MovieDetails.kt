package com.example.moviesvapp.ui.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.moviesvapp.R

@Composable
fun MovieDetails(poster: String, type: String, year: String) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_16dp))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = poster),
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier.height(dimensionResource(id = R.dimen.height_200dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_16dp)))

        Text(
            text = stringResource(R.string.category),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            type,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_8dp))
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_16dp)))

        Text(
            text = stringResource(R.string.release_date),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            year,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_8dp))
        )
    }
}
