package com.example.igormattos.overmovie.ui.adapter.favoriteadapter

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.RowFavoritesBinding
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.MovieDB

class FavoriteViewHolder(private val binding: RowFavoritesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var movieTitle = binding.textTitle
    private var movieRate = binding.textRate

    fun bind(movie: MovieDB, onclick: (Int) -> Unit, onClickDelete: (MovieDB) -> Unit) {
        movieTitle.text = movie.title
        movieRate.text = String.format("%.1f", movie.voteAverage)

        val requestOption = RequestOptions()
            .placeholder(R.drawable.poster_placeholder)
            .error(R.drawable.person_placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(Constants.URL.IMAGE_BASE + movie.posterPath)
            .into(binding.imgMoviePoster)


        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(Constants.URL.IMAGE_BASE + movie.backdropPath)
            .into(binding.imgBack)

        binding.imgMoviePoster.setOnClickListener {
            onclick(movie.id)
        }

        binding.imgFavorite.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remove")
                .setMessage("Do you want to remove this movie?")
                .setPositiveButton("Yes") { _, _ ->
                    onClickDelete(movie)
                }
                .setNeutralButton("Cancel", null)
                .show()
            true
        }

    }
}