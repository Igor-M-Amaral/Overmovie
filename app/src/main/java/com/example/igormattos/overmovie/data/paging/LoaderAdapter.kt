package com.example.igormattos.overmovie.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.igormattos.overmovie.databinding.LoaderItemBinding

class LoaderAdapter : LoadStateAdapter<LoaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(LoaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}