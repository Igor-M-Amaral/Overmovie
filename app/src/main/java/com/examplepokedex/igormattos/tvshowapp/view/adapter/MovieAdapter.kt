package com.examplepokedex.igormattos.tvshowapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examplepokedex.igormattos.tvshowapp.databinding.RowListBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.Movie

class MovieAdapter(private val moviesList: List<Movie>): RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(RowListBinding.inflate(LayoutInflater.from(parent.context), parent, false))    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

//    fun updateMovies(list: List<Movie>) {
//        movieList = list
//    }
}