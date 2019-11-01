package com.vivekvishwanath.roomsprintchallenge.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.vivekvishwanath.roomsprintchallenge.model.FavoriteMovie

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(favoriteMovie: FavoriteMovie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(favoriteMovie: FavoriteMovie)

    @Delete
    fun deleteMovie(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movies_table ORDER BY title ASC")
    fun getFavoriteMovies(): LiveData<MutableList<FavoriteMovie>>

}