package com.vivekvishwanath.roomsprintchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vivekvishwanath.roomsprintchallenge.MovieInterface
import com.vivekvishwanath.roomsprintchallenge.model.FavoriteMovie
import com.vivekvishwanath.roomsprintchallenge.model.MovieOverview
import com.vivekvishwanath.roomsprintchallenge.prefs
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repo: MovieInterface) : ViewModel() {

    fun getMatchingMovies(query: String) =
        repo.getMatchingMovies(prefs.getString("api_key", "")!!, query)

    fun getFavoriteMovies() = repo.getFavoriteMovies()

    fun insertMovie(movie: FavoriteMovie) = repo.insertMovie(movie)

    fun deleteMovie(movie: FavoriteMovie) = repo.deleteMovie(movie)

}