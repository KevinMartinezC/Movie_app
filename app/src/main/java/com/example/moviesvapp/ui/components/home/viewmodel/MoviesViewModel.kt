package com.example.moviesvapp.ui.components.home.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesvapp.model.Movie
import com.example.moviesvapp.model.RetrofitInstance
import com.example.moviesvapp.model.getApiKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(context: Context) : ViewModel() {
    val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()
    val isLoading = mutableStateOf(false)

    private val omdbApi = RetrofitInstance.omdbApi
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun searchMovies(query: String) {
        Log.wtf("MOVIS","${query}")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiKey = sharedPreferences.getApiKey()
                    ?: throw IllegalStateException("API Key not found")
                Log.wtf("MOVIS","${apiKey}")

                val trimmedQuery = query.trim()
                val response = omdbApi.searchMovies(apiKey, trimmedQuery)
                withContext(Dispatchers.Main) {
                    if (response.response == "True") {
                        _movies.update {
                            response.search
                        }
                        Log.wtf("MOVIS","${response.search}")

                        isLoading.value = false
                    } else {
                        _movies.update {
                            emptyList()
                        }

                        isLoading.value = false
                    }
                }
            } catch (e: Exception) {
                Log.wtf("MOVIS","${e.message}")

                withContext(Dispatchers.Main) {
                    _movies.update {
                        emptyList()
                    }
                    isLoading.value = false
                }
            }
        }
    }

}
