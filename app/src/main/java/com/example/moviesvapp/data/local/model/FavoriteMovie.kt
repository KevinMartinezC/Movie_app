package com.example.moviesvapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
    @PrimaryKey
    @ColumnInfo(name = "imdbID")
    val imdbID: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "year")
    val year: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "poster")
    val poster: String,
)
