package com.kotlin.andi.mymovies.utils

import com.kotlin.andi.mymovies.data.local.entity.GenreEntity
import com.kotlin.andi.mymovies.data.local.entity.MovieEntity
import com.kotlin.andi.mymovies.data.remote.ResultsItem
import com.kotlin.andi.mymovies.data.remote.response.GenresItem
import com.kotlin.andi.mymovies.domain.model.Genre
import com.kotlin.andi.mymovies.domain.model.Movie

object DataMapper {

    fun genreResponseToGenreEntities(input: List<GenresItem>): List<GenreEntity> {
        val genreList = ArrayList<GenreEntity>()
        input.map {
            val genre = GenreEntity(
                it.name,
                it.id
            )
            genreList.add(genre)
        }
        return genreList
    }

    fun mapResponseToMovieEntities(input: List<ResultsItem>, query: String): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.overview,
                it.originalLanguage,
                it.originalTitle,
                it.video,
                it.title,
            //  genreIds,
                it.posterPath,
                it.backdropPath,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.id,
                it.adult,
                it.voteCount,
                query
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapGenreEntitiesToGenreDomain(input: GenreEntity) = Genre(
        name = input.name,
        id = input.id
    )

    fun mapMovieEntitiesToMovieDomain(input: MovieEntity,query: String) = Movie(
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        //genre = input.genreIds,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        id = input.id,
        adult = input.adult,
        voteCount = input.voteCount,
        genre = query
    )

}
