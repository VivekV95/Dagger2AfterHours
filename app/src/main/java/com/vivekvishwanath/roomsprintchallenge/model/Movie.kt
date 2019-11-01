package com.vivekvishwanath.roomsprintchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

class MovieDBResponse(val results: MutableList<MovieOverview>)

class MovieOverview {
    var vote_average: Double = 0.toDouble()
        private set
    var backdrop_path: String? = null
        private set
    var isAdult: Boolean = false
        private set
    var id: Int = 0
        private set
    var title: String? = null
        private set
    var overview: String? = null
        private set
    var original_language: String? = null
        private set
    var release_date: String? = null
        private set
    var original_title: String? = null
        private set
    var vote_count: Int = 0
        private set
    var poster_path: String? = null
        private set
    var isVideo: Boolean = false
        private set
    var popularity: Double = 0.toDouble()
        private set
}

@Entity(tableName = "favorite_movies_table")
data class FavoriteMovie(
    val title: String,
    val overview: String,
    val poster_path: String,
    var isWatched: Boolean = false,
    @PrimaryKey
    val id: Int = 0
)