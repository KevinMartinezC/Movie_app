package com.example.moviesvapp.ui.components.home.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesvapp.model.RetrofitInstance
import com.example.moviesvapp.model.getApiKey
import com.example.moviesvapp.ui.components.home.MovieUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(context: Context) : ViewModel() {

    private val omdbApi = RetrofitInstance.omdbApi
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(
        MovieUiState(
            movies = emptyList(),
            isLoading = false
        )
    )
    val uiState = _uiState.asStateFlow()

    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiKey = sharedPreferences.getApiKey()
                    ?: throw IllegalStateException("API Key not found")
                val trimmedQuery = query.trim()
                val response = omdbApi.searchMovies(apiKey, trimmedQuery)
                withContext(Dispatchers.Main) {
                    if (response.response == "True") {
                        _uiState.update {
                            it.copy(movies = response.search, isLoading = false)
                        }
                    } else {
                        _uiState.update {
                            it.copy(movies = emptyList(), isLoading = false)
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.update {
                        it.copy(movies = emptyList(), isLoading = false)
                    }
                }
            }
        }
    }
}



