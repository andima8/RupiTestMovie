package com.kotlin.andi.mymovies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val backdropPath: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val posterPath: String? = null
) : Parcelable
