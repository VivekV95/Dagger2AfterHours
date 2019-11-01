package com.vivekvishwanath.roomsprintchallenge

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.vivekvishwanath.roomsprintchallenge.database.FavoriteMovieDao
import com.vivekvishwanath.roomsprintchallenge.database.FavoriteMovieDatabase
import com.vivekvishwanath.roomsprintchallenge.di.DaggerAppComponent
import com.vivekvishwanath.roomsprintchallenge.repository.MovieRepository

val prefs: SharedPreferences by lazy {
    App.prefs
}

class App: Application() {

    val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .bindApplication(this)
            .build()
    }

    companion object {
        lateinit var repo: MovieInterface
        lateinit var prefs: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()

        prefs = getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("api_key", "b98f8f717026d85eb364fe4ac55cd214").commit()
    }
}