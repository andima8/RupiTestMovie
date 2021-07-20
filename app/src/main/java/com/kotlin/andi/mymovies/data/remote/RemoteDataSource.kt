package com.kotlin.andi.mymovies.data.remote

import android.util.Log
import com.kotlin.andi.mymovies.BuildConfig
import com.kotlin.andi.mymovies.data.remote.api.ApiService
import com.kotlin.andi.mymovies.data.remote.response.GenresItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getGenre(): Flow<ApiResponse<List<GenresItem>>> {
        return flow {
            try {
                val response = apiService.getGenre(BuildConfig.API_KEY)
                val dataArray = response.genres
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.genres))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source M", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovies(query: String): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getMovies(BuildConfig.API_KEY,"en-US",query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source M", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
