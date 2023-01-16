package com.example.igormattos.overmovie.ui.adapter.movieadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.databinding.RowMoviesBinding

class SimilarAdapter(private val onclick: (Int) -> Unit): ListAdapter<MoviesResult, MovieViewHolder>(MoviesResultCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(RowMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it, onclick) }
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