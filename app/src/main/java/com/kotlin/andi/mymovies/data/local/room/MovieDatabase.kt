package com.kotlin.andi.mymovies.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.andi.mymovies.data.local.entity.GenreEntity
import com.kotlin.andi.mymovies.data.local.entity.MovieEntity

@Database(entities = [GenreEntity::class, MovieEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
