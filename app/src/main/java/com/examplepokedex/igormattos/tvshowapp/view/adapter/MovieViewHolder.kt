package com.examplepokedex.igormattos.tvshowapp.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examplepokedex.igormattos.tvshowapp.databinding.RowListBinding
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.Movie

class MovieViewHolder(private val binding: RowListBinding) : RecyclerView.ViewHolder(binding.root) {

    private var movieTitle = binding.movieTitle
    private val date = binding.movieReleaseDate

    fun bind(movie: Movie) {

        movieTitle.text = movie.title
        date.text = movie.release

        Glide.with(itemView.context)
            .load(Constants.URL.IMAGE_BASE + movie.poster)
            .into(binding.moviePoster)

    }
}