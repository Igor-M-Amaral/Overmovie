package com.examplepokedex.igormattos.tvshowapp.view.adapter.castadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examplepokedex.igormattos.tvshowapp.databinding.RowCastBinding
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.CastResult

class CastViewHolder(private val bindind: RowCastBinding) : RecyclerView.ViewHolder(bindind.root) {

    private var name = bindind.textName

    private var characterName = bindind.textCharacter

    private var department = bindind.textDepartment

    fun bind(cast: CastResult) {

        name.text = cast.name
        characterName.text = cast.character
        department.text = cast.known_for_department

        Glide.with(itemView.context)
            .load(Constants.URL.IMAGE_BASE + cast.profile_path)
            .into(bindind.imgCastPhoto)


    }
}