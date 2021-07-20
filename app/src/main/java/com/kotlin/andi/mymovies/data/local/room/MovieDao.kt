package com.kotlin.andi.mymovies.data.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.andi.mymovies.data.local.entity.GenreEntity
import com.kotlin.andi.mymovies.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    // Insert to DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genreEntity: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(moviesEntity: List<MovieEntity>)

    // Select Data
    @Query("SELECT * FROM genre_table")
    fun readGenre(): DataSource.Factory<Int, GenreEntity>

    @Query("SELECT * FROM movie_table WHERE genre = :genre")
    fun readMovie(genre: String): DataSource.Factory<Int, MovieEntity>

}
