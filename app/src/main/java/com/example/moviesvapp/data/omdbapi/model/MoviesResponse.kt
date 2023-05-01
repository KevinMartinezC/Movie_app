package com.example.moviesvapp.data.omdbapi.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("Search")
    val search: List<Movie>,
    val totalResults: String,
    @SerializedName("Response")
    val response: String
)

