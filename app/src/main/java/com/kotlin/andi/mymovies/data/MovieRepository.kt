package com.kotlin.andi.mymovies.data

import com.kotlin.andi.mymovies.data.local.LocalDataSource
import com.kotlin.andi.mymovies.data.remote.RemoteDataSource
import com.kotlin.andi.mymovies.domain.repository.IMoviesRepository

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMoviesRepository {
}
