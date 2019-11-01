package com.vivekvishwanath.roomsprintchallenge.retrofit

import com.google.gson.GsonBuilder
import com.vivekvishwanath.roomsprintchallenge.model.MovieDBResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofitService = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MovieDBApiInterface::class.java)
    }

    fun getMatchingMovies(apiKey: String, query: String): Call<MovieDBResponse> {
        return retrofitService.getMatchingMovies(apiKey, query)
    }
}
