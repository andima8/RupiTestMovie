package com.kotlin.andi.mymovies.data.remote.api

import com.kotlin.andi.mymovies.data.remote.ResponseMovies
import com.kotlin.andi.mymovies.data.remote.response.ResponseGenre
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String
    ): ResponseGenre

    @GET("search/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language=en-US") language: String,
        @Query("query") query: String
    ): ResponseMovies
}
