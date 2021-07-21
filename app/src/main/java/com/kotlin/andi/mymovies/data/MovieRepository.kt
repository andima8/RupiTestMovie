package com.kotlin.andi.mymovies.data

import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kotlin.andi.mymovies.data.local.LocalDataSource
import com.kotlin.andi.mymovies.data.remote.ApiResponse
import com.kotlin.andi.mymovies.data.remote.RemoteDataSource
import com.kotlin.andi.mymovies.data.remote.ResultsItem
import com.kotlin.andi.mymovies.data.remote.response.GenresItem
import com.kotlin.andi.mymovies.domain.model.Genre
import com.kotlin.andi.mymovies.domain.model.Movie
import com.kotlin.andi.mymovies.domain.repository.IMoviesRepository
import com.kotlin.andi.mymovies.utils.DataMapper
import com.kotlin.andi.mymovies.vo.Resource
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMoviesRepository {

    companion object {
        const val LOAD_SIZE = 5
        //const val PAGE_SIZE = 5
    }

    override fun getGenre(): Flow<Resource<PagedList<Genre>>> =
        object : NetworkBoundResources<PagedList<Genre>, List<GenresItem>>() {
            override fun loadFromDB(): Flow<PagedList<Genre>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_SIZE)
                    .setPageSize(LOAD_SIZE)
                    .build()
                return LivePagedListBuilder(localDataSource.getGenre().map {
                    DataMapper.mapGenreEntitiesToGenreDomain(it)
                }, config).build().asFlow()
            }

            override fun shouldFetch(data: PagedList<Genre>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GenresItem>>> =
                remoteDataSource.getGenre()

            override suspend fun saveCallResult(data: List<GenresItem>) {
                val genreList = DataMapper.genreResponseToGenreEntities(data)
                localDataSource.addGenre(genreList)
            }
        }.asFlow()

    override fun getMovie(query: String): Flow<Resource<PagedList<Movie>>> =
        object : NetworkBoundResources<PagedList<Movie>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<PagedList<Movie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_SIZE)
                    .setPageSize(LOAD_SIZE)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovie(query).map {
                    DataMapper.mapMovieEntitiesToMovieDomain(it,query)
                }, config).build().asFlow()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getMovies(query)

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponseToMovieEntities(data,query)
                localDataSource.addMovie(movieList)
            }
        }.asFlow()


}
