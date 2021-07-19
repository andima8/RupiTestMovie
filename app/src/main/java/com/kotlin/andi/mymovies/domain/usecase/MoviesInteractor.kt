package com.kotlin.andi.mymovies.domain.usecase

import com.kotlin.andi.mymovies.domain.repository.IMoviesRepository

class MoviesInteractor(private val iMoviesRepository: IMoviesRepository) : MoviesUseCase {

}
