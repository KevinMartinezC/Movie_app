package com.example.moviesvapp.data.utils

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

enum class Filter2(val display:String){
    ALL("ALL"),
    MOVIES("Movies")
}
