package com.examplepokedex.igormattos.tvshowapp.ui.adapter.castadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.examplepokedex.igormattos.tvshowapp.R
import com.examplepokedex.igormattos.tvshowapp.databinding.RowCastBinding
import com.examplepokedex.igormattos.tvshowapp.utils.Constants
import com.examplepokedex.igormattos.tvshowapp.data.model.CastResult

class CastViewHolder(private val bindind: RowCastBinding) : RecyclerView.ViewHolder(bindind.root) {

    private var name = bindind.textName

    private var characterName = bindind.textCharacter

    private var department = bindind.textDepartment

    fun bind(cast: CastResult) {

        name.text = cast.name
        characterName.text = cast.character
        department.text = cast.known_for_department

        val resquestOption = RequestOptions()
            .placeholder(R.drawable.person_placeholder)
            .error(R.drawable.person_placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(resquestOption)
            .load(Constants.URL.IMAGE_BASE + cast.profile_path)
            .into(bindind.imgCastPhoto)


    }
}