package com.vivekvishwanath.roomsprintchallenge.di

import android.app.Application
import com.vivekvishwanath.roomsprintchallenge.MovieInterface
import com.vivekvishwanath.roomsprintchallenge.database.FavoriteMovieDao
import com.vivekvishwanath.roomsprintchallenge.database.FavoriteMovieDatabase
import com.vivekvishwanath.roomsprintchallenge.repository.DeleteMovieAsyncTask
import com.vivekvishwanath.roomsprintchallenge.repository.InsertMovieAsyncTask
import com.vivekvishwanath.roomsprintchallenge.repository.MovieRepository
import com.vivekvishwanath.roomsprintchallenge.repository.UpdateMovieAsyncTask
import com.vivekvishwanath.roomsprintchallenge.retrofit.MovieDBApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofitInstance(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideMovieService(retrofit: Retrofit) =
        retrofit.create(MovieDBApiInterface::class.java)

    @Singleton
    @Provides
    @JvmStatic
    //make application context
    fun provideFavoriteMoviesDao(application: Application) =
        FavoriteMovieDatabase
            .getInstance(application)
            .getFavoriteMovieDao()

    @Singleton
    @Provides
    @JvmStatic
    fun provideMovieInterface(movieService: MovieDBApiInterface,
                              favoriteMovieDao: FavoriteMovieDao,
                              insertMovieAsyncTask: Provider<InsertMovieAsyncTask>,
                              updateMovieAsyncTask: Provider<UpdateMovieAsyncTask>,
                              deleteMovieAsyncTask: Provider<DeleteMovieAsyncTask>): MovieInterface =
        MovieRepository(movieService, favoriteMovieDao, insertMovieAsyncTask, updateMovieAsyncTask, deleteMovieAsyncTask)



}