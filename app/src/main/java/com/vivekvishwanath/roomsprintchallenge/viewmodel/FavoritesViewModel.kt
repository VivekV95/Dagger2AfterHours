package com.vivekvishwanath.roomsprintchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.vivekvishwanath.roomsprintchallenge.MovieInterface
import com.vivekvishwanath.roomsprintchallenge.model.FavoriteMovie
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val repo: MovieInterface) : ViewModel() {

    fun getFavoriteMovies() = repo.getFavoriteMovies()

    fun deleteMovie(movie: FavoriteMovie) = repo.deleteMovie(movie)

    fun updateMovie(movie: FavoriteMovie) = repo.updateMovie(movie)

}