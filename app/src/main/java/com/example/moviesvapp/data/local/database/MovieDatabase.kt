package com.example.moviesvapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesvapp.data.local.dao.MovieDao
import com.example.moviesvapp.data.local.model.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
