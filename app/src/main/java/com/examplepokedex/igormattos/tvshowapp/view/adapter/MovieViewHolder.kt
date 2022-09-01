package com.examplepokedex.igormattos.tvshowapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examplepokedex.igormattos.tvshowapp.databinding.RowListBinding
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.PopularResult
import com.examplepokedex.igormattos.tvshowapp.services.model.UpcomingResult

class MovieViewHolder(private val binding: RowListBinding) : RecyclerView.ViewHolder(binding.root) {

    private var movieTitle = binding.textTitle
    private val date = binding.textReleaseDate

    fun bind(upcomingResult: UpcomingResult) {

        movieTitle.text = upcomingResult.title
        date.text = upcomingResult.release_date
        upcomingResult.poster_path

        Glide.with(itemView.context)
            .load(Constants.URL.IMAGE_BASE + upcomingResult.poster_path)
            .into(binding.imgMoviePoster)

    }
}