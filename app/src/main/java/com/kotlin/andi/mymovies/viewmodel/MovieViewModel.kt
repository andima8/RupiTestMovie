package com.kotlin.andi.mymovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kotlin.andi.mymovies.domain.usecase.MoviesUseCase

class MovieViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    val getGenre = moviesUseCase.getGenre().asLiveData()
    fun getMovie(query: String) = moviesUseCase.getMovie(query).asLiveData()

}
