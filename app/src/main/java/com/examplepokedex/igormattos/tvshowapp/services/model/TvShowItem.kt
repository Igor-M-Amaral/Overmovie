package com.examplepokedex.igormattos.tvshowapp.services.model

data class TvShowItem(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: String,
    val image: Image,
    val summary: String
)