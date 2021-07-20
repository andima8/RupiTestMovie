package com.kotlin.andi.mymovies.domain.usecase

import androidx.paging.PagedList
import com.kotlin.andi.mymovies.domain.model.Genre
import com.kotlin.andi.mymovies.domain.model.Movie
import com.kotlin.andi.mymovies.domain.repository.IMoviesRepository
import com.kotlin.andi.mymovies.vo.Resource
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val iMoviesRepository: IMoviesRepository) : MoviesUseCase {
    override fun getGenre(): Flow<Resource<PagedList<Genre>>> =
        iMoviesRepository.getGenre()

    override fun getMovie(query: String): Flow<Resource<PagedList<Movie>>> =
        iMoviesRepository.getMovie(query)

}
