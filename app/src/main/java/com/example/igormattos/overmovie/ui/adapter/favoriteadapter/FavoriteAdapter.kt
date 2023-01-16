package com.example.igormattos.overmovie.ui.adapter.favoriteadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.igormattos.overmovie.databinding.RowFavoritesBinding
import com.example.igormattos.overmovie.data.model.MovieDB

class FavoriteAdapter(private val onclick: (Int) -> Unit, private val onClickDelete: (MovieDB) -> Unit) : ListAdapter<MovieDB, FavoriteViewHolder>(MovieDBToCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(RowFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, onclick, onClickDelete)
    }

    class MovieDBToCallBack: DiffUtil.ItemCallback<MovieDB>(){
        override fun areItemsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean {
            return oldItem.title == newItem.title
        }

    }
}

