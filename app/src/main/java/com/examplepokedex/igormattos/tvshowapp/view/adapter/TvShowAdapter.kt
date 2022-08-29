package com.examplepokedex.igormattos.tvshowapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examplepokedex.igormattos.tvshowapp.databinding.RowListBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.TvShowItem

class TvShowAdapter: RecyclerView.Adapter<TvShowViewHolder>() {

//    private val listShows: List<TvShowItem> = arrayListOf()
    private var listShows = mutableListOf<TvShowItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(RowListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listShows[position])
    }

    override fun getItemCount(): Int {
        return listShows.size
    }

    fun setTvShowList(list: List<TvShowItem>) {
        this.listShows = listShows.toMutableList()
        notifyDataSetChanged()
    }

}