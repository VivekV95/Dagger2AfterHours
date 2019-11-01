package com.vivekvishwanath.roomsprintchallenge.di

import com.vivekvishwanath.roomsprintchallenge.database.FavoriteMovieDao
import com.vivekvishwanath.roomsprintchallenge.repository.DeleteMovieAsyncTask
import com.vivekvishwanath.roomsprintchallenge.repository.InsertMovieAsyncTask
import com.vivekvishwanath.roomsprintchallenge.repository.UpdateMovieAsyncTask
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AsyncTaskModule {

    @Provides
    @JvmStatic
    fun provideInsertAsyncTask(favoriteMovieDao: FavoriteMovieDao) =
        InsertMovieAsyncTask(favoriteMovieDao)

    @Provides
    @JvmStatic
    fun updateMovieAsyncTask(favoriteMovieDao: FavoriteMovieDao) =
        UpdateMovieAsyncTask(favoriteMovieDao)

    @Provides
    @JvmStatic
    fun deleteMovieAsyncTask(favoriteMovieDao: FavoriteMovieDao) =
        DeleteMovieAsyncTask(favoriteMovieDao)
}