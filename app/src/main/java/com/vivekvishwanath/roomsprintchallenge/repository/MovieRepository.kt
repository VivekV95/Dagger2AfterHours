package com.vivekvishwanath.roomsprintchallenge.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vivekvishwanath.roomsprintchallenge.MovieInterface
import com.vivekvishwanath.roomsprintchallenge.database.FavoriteMovieDao
import com.vivekvishwanath.roomsprintchallenge.model.FavoriteMovie
import com.vivekvishwanath.roomsprintchallenge.model.MovieDBResponse
import com.vivekvishwanath.roomsprintchallenge.model.MovieOverview
import com.vivekvishwanath.roomsprintchallenge.retrofit.MovieDBApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class MovieRepository (
    private val movieService: MovieDBApiInterface,
    private val favoriteMovieDao: FavoriteMovieDao,
    private val insertMovieAsyncTask: Provider<InsertMovieAsyncTask>,
    private val updateMovieAsyncTask: Provider<UpdateMovieAsyncTask>,
    private val deleteMovieAsyncTask: Provider<DeleteMovieAsyncTask>) : MovieInterface {

    override fun getMatchingMovies(
        apiKey: String,
        query: String
    ): LiveData<MutableList<MovieOverview>> {
        val matchingMovies = MutableLiveData<MutableList<MovieOverview>>()
        movieService
            .getMatchingMovies(apiKey, query)
            .enqueue(object : Callback<MovieDBResponse> {
                override fun onFailure(call: Call<MovieDBResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<MovieDBResponse>,
                    response: Response<MovieDBResponse>
                ) {
                    if (response.code() == 200) {
                        matchingMovies.value = response.body()?.results
                    }
                }
            })
        return matchingMovies
    }

    // private var favoriteMovies = favoriteMovieDao.getFavoriteMovies()

    override fun getFavoriteMovies(): LiveData<MutableList<FavoriteMovie>> {
        //FavoriteMoviesAsyncTask(favoriteMovies).execute()
        return favoriteMovieDao.getFavoriteMovies()
    }

    override fun insertMovie(favoriteMovie: FavoriteMovie) {
        insertMovieAsyncTask.get().execute(favoriteMovie)
    }

    override fun updateMovie(favoriteMovie: FavoriteMovie) {
        updateMovieAsyncTask.get().execute(favoriteMovie)
    }

    override fun deleteMovie(favoriteMovie: FavoriteMovie) {
        deleteMovieAsyncTask.get().execute(favoriteMovie)
    }
}

class InsertMovieAsyncTask(private val favoriteMovieDao: FavoriteMovieDao): AsyncTask<FavoriteMovie, Unit, Unit>() {
    override fun doInBackground(vararg p0: FavoriteMovie?) {
        p0[0]?.let {
            favoriteMovieDao.insertMovie(it)
        }
    }
}

class UpdateMovieAsyncTask (private val favoriteMovieDao: FavoriteMovieDao): AsyncTask<FavoriteMovie, Unit, Unit>() {
    override fun doInBackground(vararg p0: FavoriteMovie?) {
        p0[0]?.let {
            favoriteMovieDao.updateMovie(it)
        }
    }
}

class DeleteMovieAsyncTask(private val favoriteMovieDao: FavoriteMovieDao): AsyncTask<FavoriteMovie, Unit, Unit>() {
    override fun doInBackground(vararg p0: FavoriteMovie?) {
        p0[0]?.let {
            favoriteMovieDao.deleteMovie(it)
        }
    }
}




