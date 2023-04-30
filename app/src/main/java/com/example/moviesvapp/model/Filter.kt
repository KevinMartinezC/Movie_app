package com.example.moviesvapp.model

sealed class Filter {
    object All : Filter()
    object Movies : Filter()
    object Series : Filter()

    override fun toString(): String {
        return when (this) {
            All -> "All"
            Movies -> "Movie"
            Series -> "Series"
        }
    }
}
