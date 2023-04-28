package com.example.moviesvapp.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.moviesvapp.R
import com.example.moviesvapp.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    uiState: MovieUiState,
    searchMovies: (String) -> Unit
) {
    val movies = uiState.movies
    val isLoading = uiState.isLoading
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    MyApplicationTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(56.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text(stringResource(R.string.search_movies)) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                searchMovies(searchQuery.text)
                            }
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = stringResource(R.string.search_icon)
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.wrapContentSize(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 56.dp)
                ) {
                    items(movies.size) { index ->
                        val movie = movies[index]
                        MovieItem(movie)
                        Divider()
                    }
                }
            }
        }
    }
}
