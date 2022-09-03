package com.examplepokedex.igormattos.tvshowapp.view.adapter.castadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examplepokedex.igormattos.tvshowapp.databinding.RowCastBinding
import com.examplepokedex.igormattos.tvshowapp.databinding.RowMoviesBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.CastModel
import com.examplepokedex.igormattos.tvshowapp.services.model.CastResult
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter.MovieViewHolder

class CastAdapter: RecyclerView.Adapter<CastViewHolder>() {

    private var castList = mutableListOf<CastResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(RowCastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = castList[position]
        holder.bind(cast)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    fun setCastList(movie: List<CastResult>){
        this.castList = movie.toMutableList()
    }
}