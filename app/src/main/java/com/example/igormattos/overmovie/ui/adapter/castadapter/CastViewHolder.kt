package com.example.igormattos.overmovie.ui.adapter.castadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.RowCastBinding
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.CastResult

class CastViewHolder(private val binding: RowCastBinding) : RecyclerView.ViewHolder(binding.root) {

    private var name = binding.textName

    private var characterName = binding.textCharacter

    private var department = binding.textDepartment

    fun bind(cast: CastResult) {

        name.text = cast.name
        characterName.text = cast.character
        department.text = cast.known_for_department

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.person_placeholder)
            .error(R.drawable.person_placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(Constants.URL.IMAGE_BASE + cast.profile_path)
            .into(binding.imgCastPhoto)


    }
}