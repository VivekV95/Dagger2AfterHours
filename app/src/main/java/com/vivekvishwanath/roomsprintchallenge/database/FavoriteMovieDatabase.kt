package com.vivekvishwanath.roomsprintchallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vivekvishwanath.roomsprintchallenge.model.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 3)
abstract class FavoriteMovieDatabase: RoomDatabase() {
    companion object{
        private var instance: FavoriteMovieDatabase? = null

        fun getInstance(context: Context): FavoriteMovieDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    FavoriteMovieDatabase::class.java, "favorite_movie_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as FavoriteMovieDatabase
        }
    }

    abstract fun getFavoriteMovieDao(): FavoriteMovieDao
}