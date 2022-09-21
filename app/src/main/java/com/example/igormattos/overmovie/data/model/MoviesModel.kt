package com.example.igormattos.overmovie.data.model

import com.google.gson.annotations.SerializedName


data class MoviesModel(
    @SerializedName("results")
    val moviesResults: List<MoviesResult>
)

data class MoviesResult(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdrop_path: String,

    @SerializedName("genre_ids")
    val genre_ids: List<Int>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("vote_average")
    val vote_average: Float,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("title")
    val title: String
)

