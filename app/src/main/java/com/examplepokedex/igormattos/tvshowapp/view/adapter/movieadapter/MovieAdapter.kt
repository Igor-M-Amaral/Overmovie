package com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examplepokedex.igormattos.tvshowapp.databinding.RowMoviesBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult

class MovieAdapter(private val onItemClicked: (MoviesResult) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    private var moviesList = mutableListOf<MoviesResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(RowMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie, onItemClicked)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setMovieList(movie: List<MoviesResult>){
        this.moviesList = movie.toMutableList()
    }

}