package com.kotlin.andi.mymovies.data

import com.kotlin.andi.mymovies.data.remote.ApiResponse
import com.kotlin.andi.mymovies.vo.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResources<ResultType, RequestType> {

  private val result: Flow<Resource<ResultType>> = flow {
   emit(Resource.loading())
   val dbSource = loadFromDB().first()
   if (shouldFetch(dbSource)) {
    emit(Resource.loading())
    when (val apiResponse = createCall().first()) {
     is ApiResponse.Success -> {
      saveCallResult(apiResponse.data)
      emitAll(loadFromDB().map { Resource.success(it) })
     }
     is ApiResponse.Empty -> {
      emitAll(loadFromDB().map { Resource.success(it) })
     }
     is ApiResponse.Error -> {
      onFetchFailed()
      emit(Resource.error<ResultType>(apiResponse.errorMessage))
     }
    }
   } else {
    emitAll(loadFromDB().map { Resource.success(it) })
   }
  }

  protected open fun onFetchFailed() {}

  protected abstract fun loadFromDB(): Flow<ResultType>

  protected abstract fun shouldFetch(data: ResultType?): Boolean

  protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

  protected abstract suspend fun saveCallResult(data: RequestType)

  fun asFlow(): Flow<Resource<ResultType>> = result

}
