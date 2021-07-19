package com.kotlin.andi.mymovies.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_table")
@Parcelize
data class MovieEntity (
    val backdropPath: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val name: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val adult: Boolean? = null,
    val posterPath: String? = null
): Parcelable
