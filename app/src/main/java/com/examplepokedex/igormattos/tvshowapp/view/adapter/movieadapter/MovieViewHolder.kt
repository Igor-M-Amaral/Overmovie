package com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examplepokedex.igormattos.tvshowapp.databinding.RowMoviesBinding
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult


class MovieViewHolder(private val binding: RowMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

    private var movieTitle = binding.textTitle
    private val date = binding.textReleaseDate

    fun bind(moviesResult: MoviesResult, onItemClicked: (MoviesResult) -> Unit) {

        movieTitle.text = moviesResult.title
        date.text = moviesResult.release_date


        Glide.with(itemView.context)
            .load(Constants.URL.IMAGE_BASE + moviesResult.poster_path)
            .into(binding.imgMoviePoster)

        itemView.setOnClickListener {
            onItemClicked(moviesResult)
        }
    }
}