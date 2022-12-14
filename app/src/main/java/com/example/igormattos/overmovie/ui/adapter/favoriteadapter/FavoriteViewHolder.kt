package com.example.igormattos.overmovie.ui.adapter.favoriteadapter

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.RowFavoritesBinding
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.utils.listener.MovieListener

class FavoriteViewHolder(private val binding: RowFavoritesBinding, private val listener: MovieListener) :
    RecyclerView.ViewHolder(binding.root) {

    private var movieTitle = binding.textTitle
    private var movieRate = binding.textRate

    fun bind(movie: MovieDB) {
        movieTitle.text = movie.title
        movieRate.text = String.format("%.1f", movie.vote_average)

        val requestOption = RequestOptions()
            .placeholder(R.drawable.poster_placeholder)
            .error(R.drawable.person_placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(Constants.URL.IMAGE_BASE + movie.poster_path)
            .into(binding.imgMoviePoster)

        binding.imgFavorite.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remove")
                .setMessage("Do you want to remove this movie?")
                .setPositiveButton("Yes") { _, _ ->
                    listener.onDeleteMovie(movie)
                }
                .setNeutralButton("Cancel", null)
                .show()
            true
        }

        binding.imgMoviePoster.setOnClickListener {
            listener.onListClick(movie.id)
        }

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(Constants.URL.IMAGE_BASE + movie.backdrop_path)
            .into(binding.imgBack)
    }
}