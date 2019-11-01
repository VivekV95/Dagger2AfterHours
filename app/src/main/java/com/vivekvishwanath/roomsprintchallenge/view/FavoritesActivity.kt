package com.vivekvishwanath.roomsprintchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vivekvishwanath.roomsprintchallenge.R
import com.vivekvishwanath.roomsprintchallenge.model.FavoriteMovie
import com.vivekvishwanath.roomsprintchallenge.model.MovieOverview
import com.vivekvishwanath.roomsprintchallenge.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.movie_list_item.view.*

class FavoritesActivity : AppCompatActivity() {

    private var favoriteMovies = hashMapOf<Int, FavoriteMovie>()
    private var favoriteViewModel: FavoritesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        favoriteViewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)

        val favoritesAdapter = MovieListAdapter(favoriteMovies.values)

        favorites_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            adapter = favoritesAdapter
        }

        favoriteViewModel?.getFavoriteMovies()?.observe(this, Observer { movies ->
            favoriteMovies.clear()
            movies.forEach {
                favoriteMovies[it.id] = it
            }
            favoritesAdapter.notifyDataSetChanged()
        })
    }

    inner class MovieListAdapter(private val movies: MutableCollection<FavoriteMovie>) :
        RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindMovie(movies.elementAt(position))
            if (movies.elementAt(position).isWatched)
                holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, android.R.color.holo_green_dark))
            else
                holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, android.R.color.white))

            holder.itemView.movie_item_parent.setOnLongClickListener {
                favoriteViewModel?.deleteMovie(movies.elementAt(position))
                true
            }

            holder.itemView.movie_item_parent.setOnClickListener {
                movies.elementAt(position).isWatched = !movies.elementAt(position).isWatched
                favoriteViewModel?.updateMovie(movies.elementAt(position))
            }
        }

        override fun getItemCount() = movies.size


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindMovie(movie: FavoriteMovie) {
                itemView.movie_title.text = movie.title
            }
        }
    }
}
