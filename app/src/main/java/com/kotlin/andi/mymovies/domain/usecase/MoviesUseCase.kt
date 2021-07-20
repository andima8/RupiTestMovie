package com.kotlin.andi.mymovies.domain.usecase

import androidx.paging.PagedList
import com.kotlin.andi.mymovies.domain.model.Genre
import com.kotlin.andi.mymovies.domain.model.Movie
import com.kotlin.andi.mymovies.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {

    fun getGenre(): Flow<Resource<PagedList<Genre>>>
    fun getMovie(query: String): Flow<Resource<PagedList<Movie>>>

}
