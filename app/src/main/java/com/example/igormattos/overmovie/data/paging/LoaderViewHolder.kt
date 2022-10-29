package com.example.igormattos.overmovie.data.paging

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.igormattos.overmovie.databinding.LoaderItemBinding

class LoaderViewHolder(binding: LoaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val progressBar = binding.progressBar

    fun bind(loadState: LoadState){
        progressBar.isVisible = loadState is LoadState.Loading
    }
}