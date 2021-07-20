package com.kotlin.andi.mymovies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    //val genreIds: List<Int?>? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = null,
    val voteAverage: Float? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null,
    val genre: String? = null
) : Parcelable
