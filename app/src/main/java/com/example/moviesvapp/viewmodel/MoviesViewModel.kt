package com.example.moviesvapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesvapp.model.Movie
import com.example.moviesvapp.model.RetrofitInstance
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
                val trimmedQuery = query.trim()
                val response = omdbApi.searchMovies(apiKey, trimmedQuery)
                withContext(Dispatchers.Main) {
                    if (response.response == "True") {
                        movies.value = response.search
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
