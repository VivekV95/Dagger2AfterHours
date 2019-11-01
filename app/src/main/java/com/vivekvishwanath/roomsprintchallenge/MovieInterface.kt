package com.vivekvishwanath.roomsprintchallenge

import androidx.lifecycle.LiveData
import com.vivekvishwanath.roomsprintchallenge.model.FavoriteMovie
import com.vivekvishwanath.roomsprintchallenge.model.MovieOverview

interface MovieInterface {

    fun getMatchingMovies(apiKey: String, query: String): LiveData<MutableList<MovieOverview>>

    fun getFavoriteMovies(): LiveData<MutableList<FavoriteMovie>>

    fun insertMovie(favoriteMovie: FavoriteMovie)

    fun updateMovie(favoriteMovie: FavoriteMovie)

    fun deleteMovie(favoriteMovie: FavoriteMovie)

}