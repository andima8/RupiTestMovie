package com.kotlin.andi.mymovies.di

import androidx.room.Room
import com.kotlin.andi.mymovies.BuildConfig
import com.kotlin.andi.mymovies.data.MovieRepository
import com.kotlin.andi.mymovies.data.local.LocalDataSource
import com.kotlin.andi.mymovies.data.local.room.MovieDatabase
import com.kotlin.andi.mymovies.data.remote.RemoteDataSource
import com.kotlin.andi.mymovies.data.remote.api.ApiService
import com.kotlin.andi.mymovies.domain.repository.IMoviesRepository
import com.kotlin.andi.mymovies.domain.usecase.MoviesInteractor
import com.kotlin.andi.mymovies.domain.usecase.MoviesUseCase
import com.kotlin.andi.mymovies.viewmodel.MovieViewModel
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
   /*     val passphrase: ByteArray = SQLiteDatabase.getBytes("andima8".toCharArray())
        val factory = SupportFactory(passphrase)*/
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movie_database"
        ).fallbackToDestructiveMigration()
            //.openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMoviesRepository> { MovieRepository(get(), get()) }
}

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}
