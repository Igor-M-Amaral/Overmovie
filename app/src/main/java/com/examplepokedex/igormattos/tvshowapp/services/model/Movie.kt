package com.examplepokedex.igormattos.tvshowapp.services.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    @SerializedName("id")
    val id: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("release_date")
    val release: String?
)