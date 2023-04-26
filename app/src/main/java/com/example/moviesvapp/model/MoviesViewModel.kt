package com.example.moviesvapp.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel : ViewModel() {
    val movies = mutableStateOf<List<Movie>>(emptyList())
    val isLoading = mutableStateOf(false)

    private val omdbApi = RetrofitInstance.omdbApi

    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiKey = "458ec391"
                val trimmedQuery = query.trim() // Trim the query
                val response = omdbApi.searchMovies(apiKey, trimmedQuery)
                withContext(Dispatchers.Main) {
                    if (response.Response == "True") {
                        movies.value = response.Search
                        isLoading.value = false
                    } else {
                        movies.value = emptyList()
                        isLoading.value = false
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    movies.value = emptyList()
                    isLoading.value = false
                }
            }
        }
    }

}
