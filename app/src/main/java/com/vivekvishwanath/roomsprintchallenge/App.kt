package com.vivekvishwanath.roomsprintchallenge

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.vivekvishwanath.roomsprintchallenge.database.FavoriteMovieDao
import com.vivekvishwanath.roomsprintchallenge.database.FavoriteMovieDatabase
import com.vivekvishwanath.roomsprintchallenge.repository.MovieRepository
import com.vivekvishwanath.roomsprintchallenge.retrofit.RetrofitInstance

val repo: MovieInterface by lazy {
    App.repo
}

val retrofitInstance: RetrofitInstance by lazy {
    App.retrofitInstance
}

val favoriteMovieDao: FavoriteMovieDao by lazy {
    App.favoriteMovieDao
}

val prefs: SharedPreferences by lazy {
    App.prefs
}

class App: Application() {
    companion object {
        lateinit var repo: MovieInterface
        lateinit var retrofitInstance: RetrofitInstance
        lateinit var favoriteMovieDao: FavoriteMovieDao
        lateinit var prefs: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()

        repo = MovieRepository()
        retrofitInstance = RetrofitInstance()
        favoriteMovieDao = FavoriteMovieDatabase.getInstance(applicationContext).getFavoriteMovieDao()
        prefs = getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("api_key", "b98f8f717026d85eb364fe4ac55cd214").commit()
    }
}