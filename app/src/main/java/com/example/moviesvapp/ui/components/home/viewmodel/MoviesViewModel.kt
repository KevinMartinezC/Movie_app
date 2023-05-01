package com.example.moviesvapp.ui.components.home.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.moviesvapp.data.utils.Filter
import com.example.moviesvapp.data.omdbapi.model.Movie
import com.example.moviesvapp.data.omdbapi.network.RetrofitInstance
import com.example.moviesvapp.data.local.model.FavoriteMovie
import com.example.moviesvapp.data.local.database.MovieDatabase
import com.example.moviesvapp.data.extensions.getApiKey
import com.example.moviesvapp.ui.components.home.MovieUiState
import com.example.moviesvapp.ui.components.home.viewmodel.ConstantMoviesModel.API_KEY_NOT_FOUND
import com.example.moviesvapp.ui.components.home.viewmodel.ConstantMoviesModel.DATABASE_NAME
import com.example.moviesvapp.ui.components.home.viewmodel.ConstantMoviesModel.RESPONSE_STATE
import com.example.moviesvapp.ui.components.login.viewmodel.Constants.PREFS_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(context: Context) : ViewModel() {

    private val omdbApi = RetrofitInstance.omdbApi
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val movieDatabase = Room.databaseBuilder(
        context.applicationContext,
        MovieDatabase::class.java, DATABASE_NAME
    ).build()

    private val _favoriteMoviesFlow = MutableStateFlow<List<FavoriteMovie>>(emptyList())
    val favoriteMoviesFlow = _favoriteMoviesFlow.asStateFlow()

    init {
        viewModelScope.launch {
            movieDatabase.movieDao().getAll().collect { favoriteMovies ->
                val favoriteImdbIDs = favoriteMovies.map { it.imdbID }.toSet()
                _uiState.update { it.copy(favoriteMovies = favoriteImdbIDs) }
                _favoriteMoviesFlow.value = favoriteMovies
            }
        }
    }

    private val _uiState = MutableStateFlow(
        MovieUiState(
            movies = emptyList(),
            isLoading = false
        )
    )

    val uiState = _uiState.asStateFlow()

    fun clearFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieDatabase.movieDao().deleteAll()
            withContext(Dispatchers.Main) {
                _favoriteMoviesFlow.value = emptyList()
                _uiState.update {
                    it.copy(favoriteMovies = emptySet())
                }
            }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteMovie = FavoriteMovie(
                imdbID = movie.imdbID,
                title = movie.title,
                year = movie.year,
                type = movie.type,
                poster = movie.poster
            )
            if (_uiState.value.favoriteMovies.contains(movie.imdbID)) {
                movieDatabase.movieDao().delete(favoriteMovie)
            } else {
                movieDatabase.movieDao().insert(favoriteMovie)
            }
        }
    }

    fun searchMovies(query: String, filter: Filter) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiKey = sharedPreferences.getApiKey()
                    ?: throw IllegalStateException(API_KEY_NOT_FOUND)
                val trimmedQuery = query.trim()
                val type = if (filter is Filter.All) null else filter.toString()
                val response = omdbApi.searchMovies(apiKey, trimmedQuery, type)
                withContext(Dispatchers.Main) {
                    if (response.response == RESPONSE_STATE) {
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

object ConstantMoviesModel{
    const val DATABASE_NAME = "movie-database"
    const val API_KEY_NOT_FOUND = "API Key not found"
    const val RESPONSE_STATE = "True"
}


