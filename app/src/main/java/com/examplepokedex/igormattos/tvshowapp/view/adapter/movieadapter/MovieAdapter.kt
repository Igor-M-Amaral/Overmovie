package com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.examplepokedex.igormattos.tvshowapp.databinding.RowMoviesBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.services.repository.listener.MovieListener

class MovieAdapter: ListAdapter<MoviesResult, MovieViewHolder>(MoviesResultCallBack()) {

    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(RowMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    fun attachListener(movieListener: MovieListener) {
        listener = movieListener
    }

    class MoviesResultCallBack : DiffUtil.ItemCallback<MoviesResult>() {
        override fun areItemsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
            return oldItem.title == newItem.title
        }

    }
}
