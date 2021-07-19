package com.kotlin.andi.mymovies.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "genre_table")
@Parcelize
data class GenreEntity(
    val name: String? = null,
    @PrimaryKey
    val id: Int? = null
): Parcelable
