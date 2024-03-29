package com.example.igormattos.overmovie.ui.adapter.movieadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.RowMoviesBinding
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.MoviesResult


class MovieViewHolder(private val binding: RowMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

    private var movieTitle = binding.textTitle
    private val date = binding.textReleaseDate

    fun bind(moviesResult: MoviesResult, onclick: (Int) -> Unit) {

        movieTitle.text = moviesResult.title
        date.text = moviesResult.release_date

        val requestOption = RequestOptions()
            .placeholder(R.drawable.poster_placeholder)
            .error(R.drawable.poster_placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(Constants.URL.IMAGE_BASE + moviesResult.poster_path)
            .into(binding.imgMoviePoster)

        binding.imgMoviePoster.setOnClickListener {
            onclick(moviesResult.id)
        }

    }
}