package com.vivekvishwanath.roomsprintchallenge.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vivekvishwanath.roomsprintchallenge.R
import com.vivekvishwanath.roomsprintchallenge.model.FavoriteMovie
import com.vivekvishwanath.roomsprintchallenge.model.MovieOverview
import com.vivekvishwanath.roomsprintchallenge.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_list_item.view.*

class SearchActivity : AppCompatActivity() {

    private val movies = mutableListOf<MovieOverview>()
    private val favoriteMovies = hashMapOf<Int, FavoriteMovie>()
    private var searchViewModel: SearchViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        val movieListAdapter = MovieListAdapter(movies)

        searchViewModel?.let { searchViewModel1 ->
            searchViewModel1.getFavoriteMovies()
                .observe(this, Observer<MutableList<FavoriteMovie>> {
                    favoriteMovies.clear()
                    it?.forEach { favoriteMovie ->
                        favoriteMovies[favoriteMovie.id] = favoriteMovie
                    }
                    movieListAdapter.notifyDataSetChanged()
                })
        }

        search_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = movieListAdapter
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchViewModel?.let { searchViewModel1 ->
                    searchViewModel1.getMatchingMovies(p0!!)
                        .observe(this@SearchActivity, Observer<MutableList<MovieOverview>> {
                            movies.clear()
                            movies.addAll(it)
                            movieListAdapter.notifyDataSetChanged()
                        })
                }
                return false
            }

        })

        favorites_button.setOnClickListener{
            Intent(this, FavoritesActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    inner class MovieListAdapter(private val movies: List<MovieOverview>) :
        RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
            )
        }

        override fun getItemCount() = movies.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindMovie(movies[position])

            holder.itemView.movie_item_parent.setBackgroundColor(
                if (favoriteMovies.containsKey(movies[position].id))
                    ContextCompat.getColor(this@SearchActivity, R.color.colorAccent)
                else
                    ContextCompat.getColor(this@SearchActivity, android.R.color.white)
            )

            holder.itemView.movie_item_parent.setOnLongClickListener {
                if (favoriteMovies.containsKey(movies[position].id)) {
                    favoriteMovies[movies[position].id]?.let { movie ->
                        searchViewModel?.deleteMovie(movie)
                        notifyItemChanged(position)
                    }
                } else {
                    val movie = FavoriteMovie(
                        movies[position].title!!,
                        movies[position].overview!!,
                        movies[position].poster_path!!,
                        false,
                        movies[position].id
                    )
                    searchViewModel?.insertMovie(movie)
                    notifyItemChanged(position)
                }
                false
            }
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindMovie(movie: MovieOverview) {
                itemView.movie_title.text = movie.title
                itemView.movie_date.text = movie.release_date
            }
        }
    }
}
