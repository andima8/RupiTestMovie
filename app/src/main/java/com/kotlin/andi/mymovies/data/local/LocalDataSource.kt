package com.kotlin.andi.mymovies.data.local

import androidx.paging.DataSource
import com.kotlin.andi.mymovies.data.local.entity.GenreEntity
import com.kotlin.andi.mymovies.data.local.entity.MovieEntity
import com.kotlin.andi.mymovies.data.local.room.MovieDao

class LocalDataSource(private val mMovieDao: MovieDao) {

    // Insert All Movies Data
    suspend fun addGenre(genreEntity: List<GenreEntity>) = mMovieDao.addGenre(genreEntity)
    suspend fun addMovie(moviesEntity: List<MovieEntity>) = mMovieDao.addMovies(moviesEntity)

    // Read Movies
    fun getGenre(): DataSource.Factory<Int, GenreEntity> = mMovieDao.readGenre()
    fun getMovie(genre: String): DataSource.Factory<Int, MovieEntity> = mMovieDao.readMovie(genre)

}
