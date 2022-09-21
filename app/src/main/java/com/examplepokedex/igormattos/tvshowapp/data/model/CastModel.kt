package com.examplepokedex.igormattos.tvshowapp.data.model

import com.google.gson.annotations.SerializedName

data class CastModel(
    @SerializedName("cast")
    val cast: List<CastResult>,

    @SerializedName("id")
    val id: Int
)


data class CastResult(
    @SerializedName("character")
    val character: String,

    @SerializedName("gender")
    val gender: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("known_for_department")
    val known_for_department: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("order")
    val order: Int,

    @SerializedName("profile_path")
    val profile_path: String
)