package com.example.moviesvapp.model

import retrofit2.http.GET
import retrofit2.http.Query

interface
OmdbApi {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String
    ): MoviesResponse
}