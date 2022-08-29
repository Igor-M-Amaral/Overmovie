package com.examplepokedex.igormattos.tvshowapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.examplepokedex.igormattos.tvshowapp.R
import com.examplepokedex.igormattos.tvshowapp.databinding.RowListBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.TvShowItem

class TvShowViewHolder(private val  binding: RowListBinding): RecyclerView.ViewHolder(binding.root) {

    private val showTitle = binding.textName
    private val language = binding.textLanguage
    private val summary = binding.textDescriptionContent
    private val genres = binding.textGenres

    fun bind(tvShow: TvShowItem) {

        showTitle.text = tvShow.name
        language.text = tvShow.language
        summary.text = tvShow.summary
        genres.text = tvShow.genres


        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(tvShow.image.medium)
            .into(binding.thumbnail)

    }
}