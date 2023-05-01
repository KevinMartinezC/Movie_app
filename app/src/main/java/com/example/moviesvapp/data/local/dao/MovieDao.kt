package com.example.moviesvapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesvapp.data.local.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM favorite_movies")
    fun getAll(): Flow<List<FavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteMovie: FavoriteMovie)

    @Delete
    suspend fun delete(favoriteMovie: FavoriteMovie)

    @Query("DELETE FROM favorite_movies")
    suspend fun deleteAll()
}
