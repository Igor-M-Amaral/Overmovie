package com.example.igormattos.overmovie.ui.adapter.castadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.igormattos.overmovie.databinding.RowCastBinding
import com.example.igormattos.overmovie.data.model.CastResult

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