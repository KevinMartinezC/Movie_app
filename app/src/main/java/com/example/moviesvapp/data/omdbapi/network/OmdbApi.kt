package com.example.moviesvapp.data.omdbapi.network

import com.example.moviesvapp.data.omdbapi.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String,
        @Query("type") type: String? = null
    ): MoviesResponse
}