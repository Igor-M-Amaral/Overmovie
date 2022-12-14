package com.example.igormattos.overmovie.ui.adapter.favoriteadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.igormattos.overmovie.databinding.RowFavoritesBinding
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.utils.listener.MovieListener

class FavoriteAdapter : ListAdapter<MovieDB, FavoriteViewHolder>(MovieDBToCallBack()) {

    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(RowFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    fun attachListener(favoriteListener: MovieListener){
        listener = favoriteListener
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

