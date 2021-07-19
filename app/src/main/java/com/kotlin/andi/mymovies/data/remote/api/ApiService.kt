package com.kotlin.andi.mymovies.data.remote.api

import com.kotlin.andi.mymovies.data.remote.ResponseGenre
import com.kotlin.andi.mymovies.data.remote.ResponseMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String
    ): ResponseGenre

    @GET("search/collection?api_key={api_key}&language=en-US&query={query}")
    suspend fun getMovies(
        @Path("api_key") apiKey: String,
        @Path("query") query: String,
    ): ResponseMovies
}
