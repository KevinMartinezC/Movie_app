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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.moviesvapp.R
import com.example.moviesvapp.model.Filter
import com.example.moviesvapp.model.Movie
import com.example.moviesvapp.ui.components.home.filter.FilterDropDownMenu
import com.example.moviesvapp.ui.theme.MyApplicationTheme

@Composable
fun MoviesScreen(
    uiState: MovieUiState, searchMovies: (String, Filter) -> Unit, onToggleFavorite: (Movie) -> Unit
) {
    val movies = uiState.movies
    val isLoading = uiState.isLoading
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var selectedFilter by remember { mutableStateOf<Filter>(Filter.All) }

    LaunchedEffect(selectedFilter) {
        if (searchQuery.text.isNotEmpty()) {
            searchMovies(searchQuery.text, selectedFilter)
        }
    }

    MyApplicationTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_64dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text(stringResource(R.string.search_movies)) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            searchMovies(searchQuery.text, selectedFilter)
                        }) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = stringResource(R.string.search_icon),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    })
                Spacer(modifier = Modifier.height(8.dp))

                FilterDropDownMenu(selectedFilter = selectedFilter, onFilterSelected = { filter ->
                    selectedFilter = filter
                })

            }
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.wrapContentSize(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier.padding(
                        bottom = dimensionResource(id = R.dimen.padding_56dp)
                    )
                ) {
                    items(movies.size) { index ->
                        val movie = movies[index]
                        MovieItem(
                            movie,
                            favoriteMovies = uiState.favoriteMovies,
                            onToggleFavorite = onToggleFavorite
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

